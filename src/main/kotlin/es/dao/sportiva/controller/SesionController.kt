package es.dao.sportiva.controller

import es.dao.sportiva.model.Sesion
import es.dao.sportiva.service.ISesionService
import es.dao.sportiva.utils.Constantes
import es.dao.sportiva.utils.Constantes.HEADER_ERROR_MESSAGE
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Aún no hay sesiones programadas para hoy en tu empresa.")
            ResponseEntity.notFound().headers(headers).build()
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
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Aún no has creado ninguna sesión para hoy.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

    @PostMapping("/crearSesion")
    fun crearSesion(@RequestBody sesion: Sesion) : ResponseEntity<Sesion> {
        return if (service.insert(sesion)) {
            ResponseEntity.ok(sesion)
        } else {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Error al crear la sesión. Comprueba los datos introducidos. Si el problema persiste contacte con el administrador.")
            ResponseEntity.badRequest().headers(headers).build()
        }
    }

}