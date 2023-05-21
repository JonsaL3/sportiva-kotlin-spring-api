package es.dao.sportiva.utils

import es.dao.sportiva.model.*
import java.time.LocalDate
import java.time.LocalDateTime

object Constantes {

    // default objects
    val DEFAULT_DATE = LocalDateTime.of(1900, 1, 1, 0, 0)
    val TIPO_EMPLEADO = "EMPLEADO"
    val TIPO_ENTRENADOR = "ENTRENADOR"

    const val AFORO_ILIMITADO = -1

    // Default messages
    val HEADER_ERROR_MESSAGE = "errorMessage"

    // default tests
    val EMPRESA_EJEMPLO_1 = Empresa(
        id = 1,
        nombre = "PEPOTE SL",
        isActivo = true
    )

    val EMPLEADO_EJEMPLO_1 = Empleado(
        id = 1,
        correo = "gonzalo@gonzalo.es",
        contrasena = "1234",
        nombre = "Gonzalo",
        apellido1 = "Racero",
        apellido2 = "Galán",
        fechaNacimiento = LocalDateTime.of(2000, 5, 8, 0, 0),
        fechaInserccion = LocalDateTime.now(),
        isActivo = true,
        imagen = null,
        cargo = "Programador full stack android",
        peso = 60.0f,
        altura = 1.70f,
        isDeporteFrecuente = false,
        isFumador = false,
        empresa = EMPRESA_EJEMPLO_1
    )

    val EMPLEADO_EJEMPLO_2 = Empleado(
        id = 2,
        correo = "jose@landazuri.es",
        contrasena = "1234",
        nombre = "Jose Julio",
        apellido1 = "Landázuri",
        apellido2 = "Diaz",
        fechaNacimiento = LocalDateTime.of(1998, 11, 11, 0, 0),
        fechaInserccion = LocalDateTime.now(),
        isActivo = true,
        imagen = null,
        cargo = "Programador full stack android",
        peso = 60.0f,
        altura = 1.70f,
        isDeporteFrecuente = false,
        isFumador = false,
        empresa = EMPRESA_EJEMPLO_1
    )

    val ENTRENADOR_EJEMPLO_1 = Entrenador(
        id = 3,
        correo = "david@david.es",
        contrasena = "1234",
        nombre = "David",
        apellido1 = "Lozano",
        apellido2 = "Neira",
        fechaNacimiento = LocalDateTime.of(2000, 11, 11, 0, 0),
        fechaInserccion = LocalDateTime.now(),
        isActivo = true,
        imagen = null,
        empresaAsignada = EMPRESA_EJEMPLO_1,
        estudios = "No se como se llama tu grado tio lo siento :C",
        sueldo = 20000.0f,
        fechaAlta = LocalDateTime.now()
    )

    val ENTRENADOR_EJEMPLO_2 = Entrenador(
        id = 4,
        correo = "marcos@marcos.es",
        contrasena = "1234",
        nombre = "Marcos",
        apellido1 = "Martinez",
        apellido2 = "Rus",
        fechaNacimiento = LocalDateTime.of(2000, 1, 1, 0, 0),
        fechaInserccion = LocalDateTime.now(),
        isActivo = true,
        imagen = null,
        empresaAsignada = EMPRESA_EJEMPLO_1,
        estudios = "Deportista de élite.",
        sueldo = 2000.0f,
        fechaAlta = LocalDateTime.now()
    )

    val ENTRENADOR_EJEMPLO_3 = Entrenador(
        id = 5,
        correo = "jose@jose.es",
        contrasena = "1234",
        nombre = "Jose",
        apellido1 = "Dominguez",
        apellido2 = "Rodriguez",
        fechaNacimiento = LocalDateTime.of(2000, 1, 1, 0, 0),
        fechaInserccion = LocalDateTime.now(),
        isActivo = true,
        imagen = null,
        empresaAsignada = EMPRESA_EJEMPLO_1,
        estudios = "Deportista de élite.",
        sueldo = 2000.0f,
        fechaAlta = LocalDateTime.now()
    )

}