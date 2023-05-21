package es.dao.sportiva.controller

import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.Sesion
import es.dao.sportiva.service.IEmpleadoInscribeSesionService
import es.dao.sportiva.service.ISesionService
import es.dao.sportiva.utils.Constantes
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/empleadoInscribeSesion")
class EmpleadoInscribeSesionController {

    @Autowired
    lateinit var service: IEmpleadoInscribeSesionService

    // TODO BORRAR
    @Autowired
    lateinit var serviceSesion : ISesionService

    @PostMapping("/inscribirEmpleadoASesion")
    fun inscribirEmpleadoASesion(@RequestBody inscripcion: EmpleadoInscribeSesion): ResponseEntity<EmpleadoInscribeSesion> {

        // Antes de nada, compruebo que no exista una inscripción para ese empleado y esa sesión
        if (service.existsInscripcionByEmpleadoInscritoAndSesionALaQueSeInscribe(inscripcion.empleadoInscrito, inscripcion.sesionALaQueSeInscribe)) {

            val headers = HttpHeaders()

            // Si no es aforo ilimitado compruebo que no se haya superado el aforo
            if (inscripcion.sesionALaQueSeInscribe.aforoMaximo != Constantes.AFORO_ILIMITADO) {
                val aforoActual = service.findAll()?.filter {
                    it.sesionALaQueSeInscribe.id == inscripcion.sesionALaQueSeInscribe.id
                }?.size ?: 0

                if (aforoActual >= inscripcion.sesionALaQueSeInscribe.aforoMaximo) {
                    headers.add(
                        HEADER_ERROR_MESSAGE,
                        "No te has podido inscribir a la sesión. El aforo está completo."
                    )
                    return ResponseEntity.badRequest().headers(headers).build()
                }

            }

            headers.add(
                HEADER_ERROR_MESSAGE,
                "Ya estás inscrito a esta sesión."
            )
            return ResponseEntity.badRequest().headers(headers).build()
        }


        return if (service.insert(inscripcion)) {
            ResponseEntity.ok(inscripcion)
        } else {
            val headers = HttpHeaders()
            headers.add(
                HEADER_ERROR_MESSAGE,
                "No te has podido inscribir a la sesión. Inténtalo de nuevo más tarde. Contacte con el administrador si el problema persiste."
            )
            ResponseEntity.badRequest().headers(headers).build()
        }

    }

    @DeleteMapping("/desinscribirEmpleadoASesion/{sesionId}/{empleadoId}")
    fun desinscribirEmpleadoASesion(
        @PathVariable("sesionId") sesionId: Int,
        @PathVariable("empleadoId") empleadoId: Int
    ): ResponseEntity<EmpleadoInscribeSesion> {

        val inscripcion = service.findAll()?.filter {
            it.sesionALaQueSeInscribe.id == sesionId && it.empleadoInscrito.id == empleadoId
        }?.firstOrNull()

        return inscripcion?.let {

            if (service.delete(it.id)) {
                ResponseEntity.ok(inscripcion)
            } else {
                val headers = HttpHeaders()
                headers.add(
                    HEADER_ERROR_MESSAGE,
                    "No te has podido desinscribir de la sesión. Inténtalo de nuevo más tarde. Contacte con el administrador si el problema persiste."
                )
                ResponseEntity.badRequest().headers(headers).build()
            }

        } ?: kotlin.run {
            val headers = HttpHeaders()
            headers.add(
                HEADER_ERROR_MESSAGE,
                "No estás inscrito a esa sesión."
            )
            ResponseEntity.badRequest().headers(headers).build()
        }

    }

    @GetMapping("/inscripcionesDePrueba")
    fun inscripcionesDePrueba(): ResponseEntity<List<EmpleadoInscribeSesion>> {

        val inscripcion = EmpleadoInscribeSesion(
            1,
            Constantes.EMPLEADO_EJEMPLO_2,
            serviceSesion.findById(14)!!,
            LocalDateTime.now()
        )

        val inscripcion2 = EmpleadoInscribeSesion(
            2,
            Constantes.EMPLEADO_EJEMPLO_1,
            serviceSesion.findById(14)!!,
            LocalDateTime.now()
        )


        return if (service.insert(inscripcion2)) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Inscripción creada correctamente.")
            ResponseEntity.ok().headers(headers).build()
        } else {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "No se ha podido crear la inscripción.")
            ResponseEntity.notFound().headers(headers).build()
        }

    }

    @GetMapping("/findInscripcionesByIdSesion/{sesionId}")
    fun findInscripcionesByIdSesion(@PathVariable("sesionId") sesionId: Int): ResponseEntity<List<EmpleadoInscribeSesion>> {

        val sesiones = service.findBySesion(sesionId)
        sesiones?.forEach { it.sesionALaQueSeInscribe.imagen = null } // Es tonteria mandar la imagen en este caso
        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Todavía no se ha inscrito nadie a esa sesión.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

    @GetMapping("/findAll")
    fun findAll(): ResponseEntity<List<EmpleadoInscribeSesion>> {

        val sesiones = service.findAll()
        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "No existe ninguna inscripción en la base de datos.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

}