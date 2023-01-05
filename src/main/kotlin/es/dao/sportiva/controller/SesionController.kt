package es.dao.sportiva.controller

import es.dao.sportiva.model.Sesion
import es.dao.sportiva.service.ISesionService
import es.dao.sportiva.utils.Constantes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sesion")
class SesionController {

    @Autowired
    lateinit var service: ISesionService

    private fun cargarSesionesPrueba() {
        service.insert(Constantes.SESION_EJEMPLO_1)
        service.insert(Constantes.SESION_EJEMPLO_2)
        service.insert(Constantes.SESION_EJEMPLO_3)
    }

    @GetMapping("/findSesionesDisponibles/{empresaId}")
    fun findSesionesDisponibles(@PathVariable("empresaId") empresaId: Int): ResponseEntity<List<Sesion>> {

        // TODO BORRAR
        cargarSesionesPrueba()

        val sesiones = service.findSesionesDisponibles(empresaId)
        val response = if (sesiones.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

    @GetMapping("/findSesionesDisponiblesByEntrenador/{entrenadorId}")
    fun findSesionesDisponiblesByEntrenador(@PathVariable("entrenadorId") entrenadorId: Int): ResponseEntity<List<Sesion>> {

        // TODO BORRAR
        cargarSesionesPrueba()

        val sesiones = service.findSesionesDisponiblesByEntrenador(entrenadorId)
        val response = if (sesiones.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

}