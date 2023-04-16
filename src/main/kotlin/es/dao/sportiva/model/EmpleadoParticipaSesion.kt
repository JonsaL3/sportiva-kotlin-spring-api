package es.dao.sportiva.model

import es.dao.sportiva.utils.Constantes
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class EmpleadoParticipaSesion (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = -1,

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO")
    var empleadoParticipante: Empleado = Empleado(),

    @ManyToOne
    @JoinColumn(name = "ID_SESION")
    var sesionEnLaQueParticipa: Sesion = Sesion(),

    var fechaParticipacion: LocalDateTime = Constantes.DEFAULT_DATE,

    ) : Serializable