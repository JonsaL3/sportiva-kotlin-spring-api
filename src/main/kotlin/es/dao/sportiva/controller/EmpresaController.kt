package es.dao.sportiva.controller

import es.dao.sportiva.model.Empresa
import es.dao.sportiva.service.IEmpresaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empresa")
class EmpresaController {

    @Autowired
    lateinit var service: IEmpresaService

    @GetMapping("/findall")
    fun findAll(): ResponseEntity<List<Empresa>> {
        val empresa = service.findAll()
        return ResponseEntity.ok(empresa)
    }

    @GetMapping("/cargarPrueba")
    fun cargarPrueba(): ResponseEntity<String> {
        try {
            service.insert(
                Empresa()
            )
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
        return ResponseEntity.ok("Datos cargados correctamente")
    }

}