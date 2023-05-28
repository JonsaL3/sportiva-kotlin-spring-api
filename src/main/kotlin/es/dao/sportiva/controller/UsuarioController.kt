package es.dao.sportiva.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Entrenador
import es.dao.sportiva.model.Usuario
import es.dao.sportiva.requests.IniciarSesionRequest
import es.dao.sportiva.requests.RegistroRequest
import es.dao.sportiva.requests.UpdateProfilePictureRequest
import es.dao.sportiva.responses.IniciarSesionResponse
import es.dao.sportiva.serialization_utils.LocalDateTimeTypeAdapter
import es.dao.sportiva.service.IEmpleadoService
import es.dao.sportiva.service.IEntrenadorService
import es.dao.sportiva.service.IUsuarioService
import es.dao.sportiva.utils.Constantes
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.apache.catalina.connector.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/usuario")
class UsuarioController {

    @Autowired
    lateinit var serviceEmpleado: IEmpleadoService

    @Autowired
    lateinit var serviceEntrenador: IEntrenadorService

    @Autowired
    lateinit var serviceUsuario: IUsuarioService

    @PostMapping("/updateProfilePicture")
    fun updateProfilePicture(@RequestBody request: UpdateProfilePictureRequest): ResponseEntity<Boolean> {

        if (request.isEntrenador) {
            return if (serviceEntrenador.updateImagenById(request.userId, request.base64) == 1) {
                ResponseEntity.ok(true)
            } else {
                val headers = HttpHeaders()
                headers.add(HEADER_ERROR_MESSAGE, "Error al actualizar la imagen.")
                ResponseEntity.badRequest().headers(headers).build()
            }
        } else {
            return if (serviceEmpleado.updateImagenById(request.userId, request.base64) == 1) {
                ResponseEntity.ok(true)
            } else {
                val headers = HttpHeaders()
                headers.add(HEADER_ERROR_MESSAGE, "Error al actualizar la imagen.")
                ResponseEntity.badRequest().headers(headers).build()
            }
        }
    }

    @PostMapping("/registrarUsuario")
    fun registrarUsario(@RequestBody request: RegistroRequest): ResponseEntity<Boolean> {

        println(request.json)

        try {

            val gson = GsonBuilder()
                .serializeSpecialFloatingPointValues()
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
                .create()

            println(request)

            val listUsuario = serviceUsuario.findAll()

            if (request.isEmpleado) {

                val empleado = gson.fromJson(request.json, Empleado::class.java)
                empleado.id = -1
                if (listUsuario?.any { usuario -> usuario.correo == empleado.correo } == true) {
                    val headers = HttpHeaders()
                    headers.add(HEADER_ERROR_MESSAGE, "El usuario: '${empleado.correo}' ya existe")
                    return ResponseEntity.badRequest().headers(headers).build()
                }

                return if (serviceEmpleado.insert(empleado)) {
                    ResponseEntity.ok(true)
                } else {
                    val headers = HttpHeaders()
                    headers.add(HEADER_ERROR_MESSAGE, "Error al insertar el empleado")
                    ResponseEntity.badRequest().headers(headers).build()
                }
            } else {

                val entrenador: Entrenador = gson.fromJson(request.json, Entrenador::class.java)
                entrenador.id = -1
                return if (serviceEntrenador.insert(entrenador)) {
                    ResponseEntity.ok(true)
                } else {
                    val headers = HttpHeaders()
                    headers.add(HEADER_ERROR_MESSAGE, "Error al insertar el entrenador")
                    ResponseEntity.badRequest().headers(headers).build()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val headers = HttpHeaders()
        headers.add(HEADER_ERROR_MESSAGE, "Error desconocido.")
        return ResponseEntity.badRequest().headers(headers).build()

    }

    @GetMapping("/cargarUsuariosPrueba")
    fun cargarUsuariosPrueba(): ResponseEntity<String> {

        return if (
            serviceEmpleado.insert(Constantes.EMPLEADO_EJEMPLO_1) &&
            serviceEmpleado.insert(Constantes.EMPLEADO_EJEMPLO_2) &&
            serviceEntrenador.insert(Constantes.ENTRENADOR_EJEMPLO_1) &&
            serviceEntrenador.insert(Constantes.ENTRENADOR_EJEMPLO_2) &&
            serviceEntrenador.insert(Constantes.ENTRENADOR_EJEMPLO_3)
        ) {
            ResponseEntity.ok("Empleados y entrenadores de prueba insertados correctamente.")
        } else {
            ResponseEntity.badRequest().build()
        }

    }

    @PostMapping("/iniciarSesion")
    fun iniciarSesion(@RequestBody request: IniciarSesionRequest): ResponseEntity<IniciarSesionResponse> {

        var usuario: Usuario? = serviceEmpleado.findByCorreo(request.correo)
        val iniciarSesionResponse = IniciarSesionResponse()

        usuario?.let { empleado ->
            if (empleado.contrasena == request.contrasena) {
                iniciarSesionResponse.tipo = Constantes.TIPO_EMPLEADO
                iniciarSesionResponse.json =
                    GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter()).create()
                        .toJson(empleado)
                return ResponseEntity.ok(iniciarSesionResponse)
            } else { // return bad request with error message
                val headers = HttpHeaders()
                headers.add(HEADER_ERROR_MESSAGE, "No existe ningún usuario con esas creedenciales.")
                return ResponseEntity.badRequest().headers(headers).build()
            }
        } ?: run {
            usuario = serviceEntrenador.findByCorreo(request.correo)
            usuario?.let { entrenador ->

                if (entrenador.contrasena == request.contrasena) {
                    iniciarSesionResponse.tipo = Constantes.TIPO_ENTRENADOR
                    iniciarSesionResponse.json =
                        GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
                            .create().toJson(entrenador)
                    return ResponseEntity.ok(iniciarSesionResponse)
                } else {
                    val headers = HttpHeaders()
                    headers.add(HEADER_ERROR_MESSAGE, "No existe ningún usuario con esas creedenciales.")
                    return ResponseEntity.badRequest().headers(headers).build()
                }
            } ?: run {
                val headers = HttpHeaders()
                headers.add(HEADER_ERROR_MESSAGE, "No existe ningún usuario con esas creedenciales.")
                return ResponseEntity.badRequest().headers(headers).build()
            }

        }

    }

}