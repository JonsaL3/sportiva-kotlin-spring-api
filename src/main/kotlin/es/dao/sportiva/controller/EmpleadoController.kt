package es.dao.sportiva.controller

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.service.IEmpleadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empleado")
class EmpleadoController {

    @Autowired
    lateinit var service: IEmpleadoService

    @GetMapping("/findall")
    fun findAll(): ResponseEntity<List<Empleado>> {
        val empleado = service.findAll()
        return ResponseEntity.ok(empleado)
    }

    @GetMapping("/cargarPrueba")
    fun cargarPrueba(): ResponseEntity<String> {
        try {
            service.insert(
                Empleado()
            )
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
        return ResponseEntity.ok("Datos cargados correctamente")
    }

}