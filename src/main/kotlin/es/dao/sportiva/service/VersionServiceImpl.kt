package es.dao.sportiva.service

import es.dao.sportiva.model.Version
import es.dao.sportiva.repository.VersionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VersionServiceImpl: IVersionService {

    @Autowired lateinit var repo: VersionRepository

    override fun insert(objeto: Version): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("\nError al insertar version: ${e.message}\n")
        }
        return exito
    }

    override fun update(objeto: Version): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("\nError al actualizar version: ${e.message}\n")
        }
        return exito
    }

    override fun delete(id: Int): Boolean {
        var exito = false
        try {
            repo.deleteById(id)
            exito = true
        } catch (e: Exception) {
            println("\nError al borrar version: ${e.message}\n")
        }
        return exito
    }

    override fun findAll(): List<Version>? {
        var lista: List<Version>? = null
        try {
            lista = repo.findAll().toList()
        } catch (e: Exception) {
            println("\nError al buscar todos los versiones: ${e.message}\n")
        }
        return lista
    }

    override fun findById(id: Int): Version? {
        var empleado: Version? = null
        try {
            empleado = repo.findById(id).orElse(null)
        } catch (e: Exception) {
            println("\nError al buscar version por id: ${e.message}\n")
        }
        return empleado
    }

}