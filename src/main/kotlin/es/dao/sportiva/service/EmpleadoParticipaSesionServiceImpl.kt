package es.dao.sportiva.service

import es.dao.sportiva.model.EmpleadoParticipaSesion
import es.dao.sportiva.repository.EmpleadoParticipaSesionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpleadoParticipaSesionServiceImpl: IEmpleadoParticipaSesionService {

    @Autowired lateinit var repo: EmpleadoParticipaSesionRepository

    override fun insert(objeto: EmpleadoParticipaSesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al insertar entrenador: ${e.message}")
        }
        return exito
    }

    override fun update(objeto: EmpleadoParticipaSesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al actualizar entrenador: ${e.message}")
        }
        return exito
    }

    override fun delete(id: Int): Boolean {
        var exito = false
        try {
            repo.deleteById(id)
            exito = true
        } catch (e: Exception) {
            println("Error al borrar entrenador: ${e.message}")
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