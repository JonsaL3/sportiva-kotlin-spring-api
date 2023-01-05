package es.dao.sportiva.responses

import java.io.Serializable

data class IniciarSesionResponse(
    var tipo: String = "",
    var json: String = ""
) : Serializable
