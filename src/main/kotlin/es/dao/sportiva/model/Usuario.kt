package es.dao.sportiva.model

import es.dao.sportiva.utils.Constantes
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Usuario(

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    open var id: Int = -1,

    @Column(unique = true)
    open var correo: String = "",

    open var contrasena: String = "",
    open var nombre: String = "",
    open var apellido1: String = "",
    open var apellido2: String = "",
    open var fechaNacimiento: LocalDateTime = Constantes.DEFAULT_DATE,
    open var fechaInserccion: LocalDateTime? = Constantes.DEFAULT_DATE,
    open var isActivo: Boolean = false,
    open var imagen: String? = null

) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Usuario) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}