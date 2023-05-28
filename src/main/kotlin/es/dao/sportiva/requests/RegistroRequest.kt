package es.dao.sportiva.requests

import java.io.Serializable

data class RegistroRequest(
    var isEmpleado: Boolean = false,
    var json: String = ""
) : Serializable
