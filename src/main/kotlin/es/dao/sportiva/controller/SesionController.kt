package es.dao.sportiva.controller

import es.dao.sportiva.model.Sesion
import es.dao.sportiva.service.IEmpleadoInscribeSesionService
import es.dao.sportiva.service.ISesionService
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sesion")
class SesionController {

    @Autowired
    lateinit var service: ISesionService

    @Autowired
    lateinit var serviceEmpleadoInscribeSesion: IEmpleadoInscribeSesionService

    @GetMapping("/findAll")
    fun findAll(): ResponseEntity<List<Sesion>> {

        val sesiones = service.findAll()
        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "No existe ninguna sesión en la base de datos.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

    @GetMapping("/findSesionesDisponibles/{empresaId}/{empleadoId}")
    fun findSesionesDisponibles(
        @PathVariable("empresaId") empresaId: Int,
        @PathVariable("empleadoId") empleadoId: Int
    ) : ResponseEntity<List<Sesion>> {

        val sesiones = service.findSesionesDisponibles(empresaId)
        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Aún no hay sesiones programadas para hoy en tu empresa.")
            ResponseEntity.notFound().headers(headers).build()
        } else {

            // Compruebo si está inscrito el empleado
            sesiones.forEach { sesion ->
                sesion.isCurrentEmpleadoInscrito = serviceEmpleadoInscribeSesion.findBySesion(sesion.id)?.any { it.empleadoInscrito.id == empleadoId } ?: false
            }

            // calculo la cantidad de gente que está inscrita a cada sesión
            sesiones.forEach { sesion ->
                val inscritos = serviceEmpleadoInscribeSesion.findBySesion(sesion.id)?.count() ?: 0
                sesion.numeroDeInscripciones = inscritos
                print(";;; -> inscritos: $inscritos")
            }

            ResponseEntity.ok(sesiones)
        }

        return response
    }

    @GetMapping("/findSesionesDisponiblesByEntrenador/{entrenadorId}")
    fun findSesionesDisponiblesByEntrenador(@PathVariable("entrenadorId") entrenadorId: Int): ResponseEntity<List<Sesion>> {

        val sesiones = service.findSesionesDisponiblesByEntrenador(entrenadorId)

        // no necesito las imágenes de los entrenadores, por eso las pongo a null
        sesiones?.flatMap { it.entrenadores }?.forEach { it.imagen = null }
        sesiones?.forEach { it.creador.imagen = null }

        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Aún no has creado ninguna sesión para hoy.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            sesiones.forEach { it.imagen = null }
            ResponseEntity.ok(sesiones) // no necesito enviar las imagenes aqui, podriá afinar la consulta en el repo pero que pereza.
        }

        return response
    }

    @PostMapping("/crearSesion")
    fun crearSesion(@RequestBody sesion: Sesion) : ResponseEntity<Sesion> {

        // Por como funciona springboot, antes debo de comprobar si la empresa del creador coincide con la empresa
        // de la sesión que se quiere crear (siempre va a ser asi, pero en un futuro el entrenador podría pertenecer a otra empresa)
        // por eso es interesante conservar la many to one de empresa en sesión
        if (sesion.creador.empresaAsignada.id == sesion.empresa?.id) {
            // Igualo las instancias para que spring sea capaz de realizar la inserción correctamente
            sesion.creador.empresaAsignada = sesion.empresa!!
        }

        sesion.creador.isActivo = true

        // Lo mismo con las empresas de los entrenadores participantes
        sesion.entrenadores.forEach {
            if (it.empresaAsignada.id == sesion.empresa?.id) {
                it.empresaAsignada = sesion.empresa!!
                it.isActivo = true
            }
        }

        return if (service.insert(sesion)) {
            ResponseEntity.ok(sesion)
        } else {
            val headers = HttpHeaders()
            headers.add(
                HEADER_ERROR_MESSAGE,
                "Error al crear la sesión. Comprueba los datos introducidos. Si el problema persiste contacte con el administrador."
            )
            ResponseEntity.badRequest().headers(headers).build()
        }
    }

}