package es.dao.sportiva.controller

import es.dao.sportiva.model.Empresa
import es.dao.sportiva.service.IEmpresaService
import es.dao.sportiva.utils.Constantes
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

    @GetMapping("/findAll")
    fun findAll() : List<Empresa>? {

        return service.findAll()

    }

}