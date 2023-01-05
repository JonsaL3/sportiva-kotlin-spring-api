package es.dao.sportiva.requests

import java.io.Serializable

data class IniciarSesionRequest (
    var correo: String = "",
    var contrasena: String = ""
) : Serializable