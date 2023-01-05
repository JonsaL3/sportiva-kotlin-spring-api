package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Entrenador
import es.dao.sportiva.repository.EntrenadorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EntrenadorServiceImpl: IEntrenadorService {

    @Autowired lateinit var repo: EntrenadorRepository

    override fun insert(objeto: Entrenador): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("\nError al insertar entrenador: ${e.message}\n")
        }
        return exito
    }

    override fun update(objeto: Entrenador): Boolean {
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

    override fun findAll(): List<Entrenador>? {
        var lista: List<Entrenador>? = null
        try {
            lista = repo.findAll().toList()
        } catch (e: Exception) {
            println("\nError al buscar todos los entrenadores: ${e.message}\n")
        }
        return lista
    }

    override fun findById(id: Int): Entrenador? {
        var entrenador: Entrenador? = null
        try {
            entrenador = repo.findById(id).orElse(null)
        } catch (e: Exception) {
            println("\nError al buscar entrenador por id: ${e.message}\n")
        }
        return entrenador
    }

    override fun findByCorreo(correo: String): Entrenador? {
        var entrenador: Entrenador? = null
        try {
            entrenador = repo.findByCorreo(correo)
        } catch (e: Exception) {
            println("\nError al buscar entrenador por correo: ${e.message}\n")
        }
        return entrenador
    }

    override fun findByEmpresaAsignada(idEmpresa: Int): List<Entrenador>? {
        var lista: List<Entrenador>? = null
        try {
            lista = repo.findByIdEmpresaAsignada(idEmpresa)
        } catch (e: Exception) {
            println("\nError al buscar entrenadores por empresa: ${e.message}\n")
        }
        return lista
    }

}