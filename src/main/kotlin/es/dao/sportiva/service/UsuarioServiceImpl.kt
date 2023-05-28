package es.dao.sportiva.service

import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Usuario
import es.dao.sportiva.repository.EmpresaRepository
import es.dao.sportiva.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl: IUsuarioService {

    @Autowired lateinit var repo: UsuarioRepository

    override fun findAll(): List<Usuario>? {
        var lista: List<Usuario>? = null
        try {
            lista = repo.findAll().toList()
        } catch (e: Exception) {
            println("Error al buscar los usuarios: ${e.message}")
        }
        return lista
    }

}