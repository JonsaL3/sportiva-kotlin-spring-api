package es.dao.sportiva.model

import es.dao.sportiva.utils.Constantes
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Entrenador(
    var estudios: String = "",
    var sueldo: Float = -1.0f,
    var fechaAlta: LocalDateTime = Constantes.DEFAULT_DATE,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPRESA")
    var empresaAsignada: Empresa = Empresa(),

) : Usuario() {

    constructor(
        id: Int,
        correo: String,
        contrasena: String,
        nombre: String,
        apellido1: String,
        apellido2: String,
        fechaNacimiento: LocalDateTime,
        fechaInserccion: LocalDateTime?,
        isActivo: Boolean,
        imagen: String?,
        estudios: String,
        sueldo: Float,
        fechaAlta: LocalDateTime,
        empresaAsignada: Empresa
    ) : this(estudios, sueldo, fechaAlta) {
        this.id = id
        this.correo = correo
        this.contrasena = contrasena
        this.nombre = nombre
        this.apellido1 = apellido1
        this.apellido2 = apellido2
        this.fechaNacimiento = fechaNacimiento
        this.fechaInserccion = fechaInserccion
        this.isActivo = isActivo
        this.imagen = imagen
        this.empresaAsignada = empresaAsignada
    }

}