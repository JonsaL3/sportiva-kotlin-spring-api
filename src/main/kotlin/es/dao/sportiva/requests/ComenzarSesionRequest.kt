package es.dao.sportiva.requests

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Sesion
import java.io.Serializable

data class ComenzarSesionRequest(
    val sesion: Sesion,
    val participaciones: List<Empleado>
) : Serializable