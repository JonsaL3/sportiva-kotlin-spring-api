package es.dao.sportiva.service

import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.Empresa

interface IEmpleadoInscribeSesionService {
    fun insert(objeto: EmpleadoInscribeSesion): Boolean
    fun update(objeto: EmpleadoInscribeSesion): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<EmpleadoInscribeSesion>?
    fun findById(id: Int): EmpleadoInscribeSesion?
    fun findBySesion(id: Int): List<EmpleadoInscribeSesion>?
}