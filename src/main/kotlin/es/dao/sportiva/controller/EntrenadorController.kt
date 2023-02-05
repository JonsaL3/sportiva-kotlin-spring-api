package es.dao.sportiva.controller

import es.dao.sportiva.model.Entrenador
import es.dao.sportiva.service.IEntrenadorService
import es.dao.sportiva.utils.Constantes
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/entrenador")
class EntrenadorController {

    @Autowired
    lateinit var service: IEntrenadorService

    private fun cargarEntrenadoresPrueba() {
        service.insert(Constantes.ENTRENADOR_EJEMPLO_1)
        service.insert(Constantes.ENTRENADOR_EJEMPLO_2)
    }

    @GetMapping("/findByEmpresaAsignada/{empresaId}")
    fun findByEmpresaAsignada(@PathVariable("empresaId") empresaId: Int): ResponseEntity<List<Entrenador>> {

        // TODO BORRAR
        cargarEntrenadoresPrueba()

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

}