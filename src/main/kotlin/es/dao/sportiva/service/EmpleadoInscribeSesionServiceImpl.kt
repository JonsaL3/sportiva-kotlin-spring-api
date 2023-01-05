package es.dao.sportiva.service

import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.repository.EmpleadoInscribeSesionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpleadoInscribeSesionServiceImpl: IEmpleadoInscribeSesionService {

    @Autowired lateinit var repo: EmpleadoInscribeSesionRepository

    override fun insert(objeto: EmpleadoInscribeSesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al insertar entrenador: ${e.message}")
        }
        return exito
    }

    override fun update(objeto: EmpleadoInscribeSesion): Boolean {
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

    override fun findAll(): List<EmpleadoInscribeSesion>? {
        return repo.findAll().toList()
    }

    override fun findById(id: Int): EmpleadoInscribeSesion? {
        return repo.findById(id).orElse(null)
    }

    override fun findBySesion(id: Int): List<EmpleadoInscribeSesion>? {
        return repo.findBySesion(id)
    }

}