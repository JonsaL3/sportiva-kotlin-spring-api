package es.dao.sportiva.service

import es.dao.sportiva.model.Empresa

interface IEmpresaService {
    fun insert(objeto: Empresa): Boolean
    fun update(objeto: Empresa): Boolean
    fun delete(id: Int): Boolean
    fun findAll(): List<Empresa>?
    fun findById(id: Int): Empresa?
}