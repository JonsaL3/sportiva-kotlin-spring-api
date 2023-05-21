package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Sesion

interface IEmpleadoInscribeSesionService {
    fun insert(objeto: EmpleadoInscribeSesion): Boolean
    fun update(objeto: EmpleadoInscribeSesion): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<EmpleadoInscribeSesion>?
    fun findById(id: Int): EmpleadoInscribeSesion?
    fun findBySesion(id: Int): List<EmpleadoInscribeSesion>?
    fun existsInscripcionByEmpleadoInscritoAndSesionALaQueSeInscribe(empleado: Empleado, sesion: Sesion): Boolean
}