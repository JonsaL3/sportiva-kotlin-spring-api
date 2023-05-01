package es.dao.sportiva.controller

import es.dao.sportiva.model.EmpleadoParticipaSesion
import es.dao.sportiva.requests.ComenzarSesionRequest
import es.dao.sportiva.service.IEmpleadoParticipaSesionService
import es.dao.sportiva.utils.Constantes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/empleadoParticipaSesion")
class EmpleadoParticipaSesionController {

    @Autowired
    lateinit var service: IEmpleadoParticipaSesionService

    @PostMapping("/comenzarSesion")
    fun comenzarSesion(@RequestBody comenzarSesionRequest: ComenzarSesionRequest): ResponseEntity<List<EmpleadoParticipaSesion>> {

        val sesion = comenzarSesionRequest.sesion
        val empresa = sesion.empresa
        val empleadosParticipantes = comenzarSesionRequest.participaciones

        empleadosParticipantes.forEach { it.empresa = empresa!! }
        sesion.creador.empresaAsignada = empresa!!
        sesion.entrenadores.removeIf { it.id == sesion.creador.id }
        sesion.isLlevadaACabo = true

        val relaciones = empleadosParticipantes.map {
            EmpleadoParticipaSesion(
                empleadoParticipante = it,
                sesionEnLaQueParticipa = sesion,
                fechaParticipacion = LocalDateTime.now(),
            )
        }

        return if (service.saveAll(relaciones)) {
            ResponseEntity.ok(relaciones)
        } else {
            val headers = HttpHeaders()
            headers.add(Constantes.HEADER_ERROR_MESSAGE, "Ha ocurrido un error al guardar las relaciones.")
            ResponseEntity.notFound().headers(headers).build()
        }

    }

}