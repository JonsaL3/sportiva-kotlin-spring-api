package es.dao.sportiva.service

import es.dao.sportiva.model.Sesion

interface ISesionService {
    fun insert(objeto: Sesion): Boolean
    fun update(objeto: Sesion): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<Sesion>?
    fun findById(id: Int): Sesion?
    fun findSesionesDisponibles(idEmpresa: Int): List<Sesion>?
    fun findSesionesDisponiblesByEntrenador(idEntrenador: Int): List<Sesion>?
}