package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado
import org.springframework.transaction.annotation.Transactional

interface IEmpleadoService {
    fun insert(objeto: Empleado): Boolean
    fun update(objeto: Empleado): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<Empleado>?
    fun findById(id: Int): Empleado?
    fun findByCorreo(correo: String): Empleado?
    @Transactional
    fun updateImagenById(id: Int, imagen: String): Int
}