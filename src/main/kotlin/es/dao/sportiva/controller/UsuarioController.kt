package es.dao.sportiva.controller

import com.google.gson.GsonBuilder
import es.dao.sportiva.model.Usuario
import es.dao.sportiva.requests.IniciarSesionRequest
import es.dao.sportiva.responses.IniciarSesionResponse
import es.dao.sportiva.serialization_utils.LocalDateTimeTypeAdapter
import es.dao.sportiva.service.IEmpleadoService
import es.dao.sportiva.service.IEntrenadorService
import es.dao.sportiva.utils.Constantes
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
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

    @GetMapping("/cargarUsuariosPrueba")
    fun cargarUsuariosPrueba() {
        serviceEmpleado.insert(Constantes.EMPLEADO_EJEMPLO_1)
        serviceEntrenador.insert(Constantes.ENTRENADOR_EJEMPLO_1)
    }

    @PostMapping("/iniciarSesion")
    fun iniciarSesion(@RequestBody request: IniciarSesionRequest): ResponseEntity<IniciarSesionResponse> {

        var usuario: Usuario? = serviceEmpleado.findByCorreo(request.correo)
        val iniciarSesionResponse = IniciarSesionResponse()

        usuario?.let { empleado ->
            if (empleado.contrasena == request.contrasena) {
                iniciarSesionResponse.tipo = Constantes.TIPO_EMPLEADO
                iniciarSesionResponse.json = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter()).create().toJson(empleado)
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
                    iniciarSesionResponse.json = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter()).create().toJson(entrenador)
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