package es.dao.sportiva.service

import es.dao.sportiva.model.Version

interface IVersionService {
    fun insert(objeto: Version): Boolean
    fun update(objeto: Version): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<Version>?
    fun findById(id: Int): Version?
}