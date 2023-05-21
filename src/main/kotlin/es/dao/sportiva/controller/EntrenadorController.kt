package es.dao.sportiva.controller

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Entrenador
import es.dao.sportiva.service.IEntrenadorService
import es.dao.sportiva.utils.Constantes
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/entrenador")
class EntrenadorController {

    @Autowired
    lateinit var service: IEntrenadorService

    @GetMapping("/findByEmpresaAsignada/{empresaId}")
    fun findByEmpresaAsignada(@PathVariable("empresaId") empresaId: Int): ResponseEntity<List<Entrenador>> {

        val entrenadores = service.findByEmpresaAsignada(empresaId)
        val response = if (entrenadores.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Tu empresa todavía no tiene entrenadores asignados.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(entrenadores)
        }

        return response
    }

    @PostMapping("/registrarEntrenador")
    fun registrarEntrenador(@RequestBody entrenador: Entrenador): ResponseEntity<Boolean> {

        val headers = HttpHeaders()

        return if(service.insert(entrenador)) {
            ResponseEntity.ok(true)
        } else {
            headers.add(Constantes.HEADER_ERROR_MESSAGE, "Ocurrió un error al registrar el usuario.")
            ResponseEntity.badRequest().headers(headers).build()
        }
    }

}