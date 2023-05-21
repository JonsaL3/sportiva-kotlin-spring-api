package es.dao.sportiva.controller

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.service.IEmpleadoService
import es.dao.sportiva.utils.Constantes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empleado")
class EmpleadoController {

    @Autowired
    lateinit var service: IEmpleadoService

    @PostMapping("/registrarEmpleado")
    fun registrarEmpleado(@RequestBody empleado: Empleado): ResponseEntity<Boolean> {

        val headers = HttpHeaders()

        return if(service.insert(empleado)){
            ResponseEntity.ok(true)
        }else{
            headers.add(Constantes.HEADER_ERROR_MESSAGE, "Ocurrió un error al registrar el usuario.")
            ResponseEntity.badRequest().headers(headers).build()
        }
    }

}