package es.dao.sportiva.model

import es.dao.sportiva.utils.Constantes
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class EmpleadoInscribeSesion (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO")
    var empleadoInscrito: Empleado = Empleado(),

    @ManyToOne
    @JoinColumn(name = "ID_SESION")
    var sesionALaQueSeInscribe: Sesion = Sesion(),

    var fechaInscripcion: LocalDateTime = Constantes.DEFAULT_DATE,

) : Serializable