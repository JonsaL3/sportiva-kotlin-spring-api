package es.dao.sportiva.service

import es.dao.sportiva.model.Sesion
import es.dao.sportiva.repository.SesionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SesionServiceImpl: ISesionService {

    @Autowired lateinit var repo: SesionRepository

    override fun insert(objeto: Sesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("\nError al insertar entrenador: ${e.message}\n")
        }
        return exito
    }

    override fun update(objeto: Sesion): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("\nError al actualizar entrenador: ${e.message}\n")
        }
        return exito
    }

    override fun delete(id: Int): Boolean {
        var exito = false
        try {
            repo.deleteById(id)
            exito = true
        } catch (e: Exception) {
            println("\nError al borrar entrenador: ${e.message}\n")
        }
        return exito
    }

    override fun findAll(): List<Sesion>? {
        var lista: List<Sesion>? = null
        try {
            lista = repo.findAll().toList()
        } catch (e: Exception) {
            println("\nError al buscar todos los entrenadores: ${e.message}\n")
        }
        return lista
    }

    override fun findById(id: Int): Sesion? {
        var entrenador: Sesion? = null
        try {
            entrenador = repo.findById(id).get()
        } catch (e: Exception) {
            println("\nError al buscar entrenador por id: ${e.message}\n")
        }
        return entrenador
    }

    override fun findSesionesDisponibles(idEmpresa: Int): List<Sesion>? {
        var lista: List<Sesion>? = null
        try {
            lista = repo.findSesionesDisponibles(idEmpresa)
        } catch (e: Exception) {
            println("\nError al buscar sesiones disponibles: ${e.message}\n")
        }
        return lista
    }

    override fun findSesionesDisponiblesByEntrenador(idEntrenador: Int): List<Sesion>? {
        var lista: List<Sesion>? = null
        try {
            lista = repo.findSesionesDisponiblesByEntrenador(idEntrenador)
        } catch (e: Exception) {
            println("\nError al buscar sesiones disponibles por entrenador: ${e.message}\n")
        }
        return lista
    }

}