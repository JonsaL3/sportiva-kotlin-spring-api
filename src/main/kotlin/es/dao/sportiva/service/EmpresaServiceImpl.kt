package es.dao.sportiva.service

import es.dao.sportiva.model.Empresa
import es.dao.sportiva.repository.EmpresaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImpl: IEmpresaService {

    @Autowired lateinit var repo: EmpresaRepository

    override fun insert(objeto: Empresa): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al insertar empresa: ${e.message}")
        }
        return exito
    }

    override fun update(objeto: Empresa): Boolean {
        var exito = false
        try {
            repo.save(objeto)
            exito = true
        } catch (e: Exception) {
            println("Error al actualizar empresa: ${e.message}")
        }
        return exito
    }

    override fun delete(id: Int): Boolean {
        var exito = false
        try {
            repo.deleteById(id)
            exito = true
        } catch (e: Exception) {
            println("Error al borrar empresa: ${e.message}")
        }
        return exito
    }

    override fun findAll(): List<Empresa>? {
        var lista: List<Empresa>? = null
        try {
            lista = repo.findAll().toList()
        } catch (e: Exception) {
            println("Error al buscar todas las empresas: ${e.message}")
        }
        return lista
    }

    override fun findById(id: Int): Empresa? {
        var empresa: Empresa? = null
        try {
            empresa = repo.findById(id).orElse(null)
        } catch (e: Exception) {
            println("Error al buscar empresa por id: ${e.message}")
        }
        return empresa
    }

}