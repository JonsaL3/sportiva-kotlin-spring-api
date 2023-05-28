package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Entrenador
import org.springframework.transaction.annotation.Transactional

interface IEntrenadorService {
    fun insert(objeto: Entrenador): Boolean
    fun update(objeto: Entrenador): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<Entrenador>?
    fun findById(id: Int): Entrenador?
    fun findByCorreo(correo: String): Entrenador?
    fun findByEmpresaAsignada(idEmpresa: Int): List<Entrenador>?
    @Transactional
    fun updateImagenById(id: Int, imagen: String): Int
}