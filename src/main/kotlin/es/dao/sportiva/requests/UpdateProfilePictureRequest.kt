package es.dao.sportiva.requests

import java.io.Serializable

data class UpdateProfilePictureRequest(
    var isEntrenador: Boolean = false,
    var userId: Int = 0,
    var base64: String = ""
) : Serializable
