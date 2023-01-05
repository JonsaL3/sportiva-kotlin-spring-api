package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado

interface IEmpleadoService {
    fun insert(objeto: Empleado): Boolean
    fun update(objeto: Empleado): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<Empleado>?
    fun findById(id: Int): Empleado?
    fun findByCorreo(correo: String): Empleado?
}