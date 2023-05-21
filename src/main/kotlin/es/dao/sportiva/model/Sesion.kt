package es.dao.sportiva.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.annotations.SerializedName
import es.dao.sportiva.utils.Constantes
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Sesion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,
    var titulo: String = "",
    var subtitulo: String = "",

    @Column(columnDefinition = "TEXT")
    var descripcion: String = "",

    var fechaInserccion: LocalDateTime = Constantes.DEFAULT_DATE,
    var fechaSesion: LocalDateTime = Constantes.DEFAULT_DATE,
    var aforoMaximo: Int = -1,
    var isLlevadaACabo: Boolean = false,

    @Column(columnDefinition = "LONGBLOB")
    var imagen: String? = "",

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPRESA")
    var empresa: Empresa? = Empresa(),

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ENTRENADOR")
    var creador: Entrenador = Entrenador(),

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "ENTRENADORES_SESION",
        joinColumns = [JoinColumn(name = "ID_SESION")],
        inverseJoinColumns = [JoinColumn(name = "ID_ENTRENADOR")]
    )
    var entrenadores: MutableList<Entrenador> = mutableListOf(),

    // No se guardan en las variables de a continuacion
    @Transient
    @JsonInclude()
    var isCurrentEmpleadoInscrito: Boolean = false,

    @Transient
    @JsonInclude()
    var numeroDeInscripciones: Int = 0,

) : Serializable {

    // all fields minus imagen constructor
    constructor(
        id: Int = -1,
        titulo: String = "",
        subtitulo: String = "",
        descripcion: String = "",
        fechaInserccion: LocalDateTime = Constantes.DEFAULT_DATE,
        fechaSesion: LocalDateTime = Constantes.DEFAULT_DATE,
        aforoMaximo: Int = -1,
        isLlevadaACabo: Boolean = false,
        empresa: Empresa? = Empresa(),
        creador: Entrenador = Entrenador(),
        entrenadores: MutableList<Entrenador> = mutableListOf(),
    ) : this(
        id,
        titulo,
        subtitulo,
        descripcion,
        fechaInserccion,
        fechaSesion,
        aforoMaximo,
        isLlevadaACabo,
        "",
        empresa,
        creador,
        entrenadores
    )


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Sesion) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}