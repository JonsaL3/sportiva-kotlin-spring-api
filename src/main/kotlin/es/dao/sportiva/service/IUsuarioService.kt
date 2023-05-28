package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Entrenador
import es.dao.sportiva.model.Usuario

interface IUsuarioService {
    fun findAll(): List<Usuario>?
}