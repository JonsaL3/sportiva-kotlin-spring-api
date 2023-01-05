package es.dao.sportiva.controller

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.EmpleadoParticipaSesion
import es.dao.sportiva.service.IEmpleadoInscribeSesionService
import es.dao.sportiva.service.IEmpleadoParticipaSesionService
import es.dao.sportiva.service.IEmpleadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empleadoParticipaSesion")
class EmpleadoParticipaSesionController {

    @Autowired
    lateinit var service: IEmpleadoParticipaSesionService

    @GetMapping("/findall")
    fun findAll(): ResponseEntity<List<EmpleadoParticipaSesion>> {
        val empleado = service.findAll()
        return ResponseEntity.ok(empleado)
    }

    @GetMapping("/cargarPrueba")
    fun cargarPrueba(): ResponseEntity<String> {
        try {
            service.insert(
                EmpleadoParticipaSesion()
            )
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
        return ResponseEntity.ok("Datos cargados correctamente")
    }

}