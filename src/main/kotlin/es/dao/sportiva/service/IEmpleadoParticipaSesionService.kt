package es.dao.sportiva.service

import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.EmpleadoParticipaSesion
import es.dao.sportiva.model.Empresa

interface IEmpleadoParticipaSesionService {
    fun insert(objeto: EmpleadoParticipaSesion): Boolean
    fun update(objeto: EmpleadoParticipaSesion): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<EmpleadoParticipaSesion>?
    fun findById(id: Int): EmpleadoParticipaSesion?
}