package es.dao.sportiva.controller

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.Sesion
import es.dao.sportiva.service.IEmpleadoInscribeSesionService
import es.dao.sportiva.service.IEmpleadoService
import es.dao.sportiva.service.ISesionService
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
@RequestMapping("/api/empleadoInscribeSesion")
class EmpleadoInscribeSesionController {

    @Autowired
    lateinit var service: IEmpleadoInscribeSesionService

    // TODO BORRAR
    @Autowired
    lateinit var serviceEmpleado: IEmpleadoService
    @Autowired
    lateinit var sesionService: ISesionService

    private fun cargarInscripcionesPrueba() {

        serviceEmpleado.insert(Constantes.EMPLEADO_EJEMPLO_1)

        sesionService.insert(Constantes.SESION_EJEMPLO_1)
        sesionService.insert(Constantes.SESION_EJEMPLO_2)
        sesionService.insert(Constantes.SESION_EJEMPLO_3)

        service.insert(Constantes.EMPLEADO_INSCRITO_SESION_1_EJEMPLO)
        service.insert(Constantes.EMPLEADO_INSCRITO_SESION_3_EJEMPLO)

    }

    @GetMapping("/findInscripcionesByIdSesion/{sesionId}")
    fun findInscripcionesByIdSesion(@PathVariable("sesionId") sesionId: Int): ResponseEntity<List<EmpleadoInscribeSesion>> {

        // TODO BORRAR
        cargarInscripcionesPrueba()

        val sesiones = service.findBySesion(sesionId)
        println("--->" + sesiones.toString())
        val response = if (sesiones.isNullOrEmpty()) {
            val headers = HttpHeaders()
            headers.add(HEADER_ERROR_MESSAGE, "Todav??a nadie se ha inscrito a esa sesi??n o la sesi??n no existe.")
            ResponseEntity.notFound().headers(headers).build()
        } else {
            ResponseEntity.ok(sesiones)
        }

        return response
    }

}