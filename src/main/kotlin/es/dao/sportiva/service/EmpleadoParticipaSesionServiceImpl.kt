package es.dao.sportiva.service

import es.dao.sportiva.model.EmpleadoParticipaSesion
import es.dao.sportiva.repository.EmpleadoParticipaSesionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpleadoParticipaSesionServiceImpl: IEmpleadoParticipaSesionService {

    @Autowired lateinit var repo: EmpleadoParticipaSesionRepository
    @Autowired lateinit var sesionService: ISesionService

    override fun insert(objeto: EmpleadoParticipaSesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al insertar empleadoParticipaSesion: ${e.message}")
        }
        return exito
    }

    override fun saveAll(objeto: List<EmpleadoParticipaSesion>): Boolean {
        var exito = false
        try {
            repo.saveAll(objeto)
            // Una vez insertadas las participaciones actualizo la sesión
            sesionService.update(objeto[0].sesionEnLaQueParticipa)
            exito = true
        } catch (e: Exception) {
            println("Error al insertar empleadoParticipaSesion y actualizar la sesion: ${e.message}")
        }
        return exito // TODO PUTA MIERDA CANCERIGENA AL INSERTAR EMPLEADO PARTICIPA WITH SESIONES WITH EMPRESA.
    }

    override fun update(objeto: EmpleadoParticipaSesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al actualizar empleadoParticipaSesion: ${e.message}")
        }
        return exito
    }

    override fun delete(id: Int): Boolean {
        var exito = false
        try {
            repo.deleteById(id)
            exito = true
        } catch (e: Exception) {
            println("Error al borrar empleadoParticipaSesion: ${e.message}")
        }
        return exito
    }

    override fun findAll(): List<EmpleadoParticipaSesion>? {
        return repo.findAll().toList()
    }

    override fun findById(id: Int): EmpleadoParticipaSesion? {
        return repo.findById(id).orElse(null)
    }

}