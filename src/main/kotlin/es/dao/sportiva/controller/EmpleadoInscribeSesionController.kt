package es.dao.sportiva.controller

import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.service.IEmpleadoInscribeSesionService
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empleadoInscribeSesion")
class EmpleadoInscribeSesionController {

    @Autowired
    lateinit var service: IEmpleadoInscribeSesionService

    @GetMapping("/findInscripcionesByIdSesion/{sesionId}")
    fun findInscripcionesByIdSesion(@PathVariable("sesionId") sesionId: Int): ResponseEntity<List<EmpleadoInscribeSesion>> {

        val sesiones = service.findBySesion(sesionId)
        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Todavía nadie se ha inscrito a esa sesión o la sesión no existe.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

}