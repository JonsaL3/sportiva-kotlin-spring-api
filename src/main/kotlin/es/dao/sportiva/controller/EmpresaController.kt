package es.dao.sportiva.controller

import es.dao.sportiva.service.IEmpresaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/empresa")
class EmpresaController {

    @Autowired
    lateinit var service: IEmpresaService

}