package es.dao.sportiva.model

import java.io.Serializable
import javax.persistence.*

@Entity
data class Empresa(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,
    var nombre: String = "",
    var isActivo: Boolean = false,

) : Serializable