package es.dao.sportiva.utils

import es.dao.sportiva.model.*
import java.time.LocalDate
import java.time.LocalDateTime

object Constantes {

    // default objects
    val DEFAULT_DATE = LocalDateTime.of(1900, 1, 1, 0, 0)
    val TIPO_EMPLEADO = "EMPLEADO"
    val TIPO_ENTRENADOR = "ENTRENADOR"

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

    val ENTRENADOR_EJEMPLO_1 = Entrenador(
        id = 2,
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
        id = 3,
        correo = "jose@jose.es",
        contrasena = "1234",
        nombre = "Joseluis",
        apellido1 = "Rodriguez",
        apellido2 = "Zapatero",
        fechaNacimiento = LocalDateTime.of(2000, 1, 1, 0, 0),
        fechaInserccion = LocalDateTime.now(),
        isActivo = true,
        imagen = null,
        empresaAsignada = EMPRESA_EJEMPLO_1,
        estudios = "Ingenieria en balones de furbo",
        sueldo = 2000.0f,
        fechaAlta = LocalDateTime.now()
    )

    val SESION_EJEMPLO_1 = Sesion(
        id = 1,
        titulo = "Sesión de prueba 1",
        subtitulo = "Es una sesion de prueba",
        descripcion = "Es una sesion de prueba Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Done",
        fechaInserccion = LocalDateTime.now(),
        fechaSesion = LocalDateTime.now().plusHours(1),
        aforoMaximo = 20,
        imagen = null,
        creador = ENTRENADOR_EJEMPLO_1,
        entrenadores = mutableListOf(ENTRENADOR_EJEMPLO_1),
        empresa = EMPRESA_EJEMPLO_1
    )

    val SESION_EJEMPLO_2 = Sesion(
        id = 2,
        titulo = "Sesión de prueba 2",
        subtitulo = "Es una sesion de prueba",
        descripcion = "Es una sesion de prueba Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Done",
        fechaInserccion = LocalDateTime.now(),
        fechaSesion = LocalDateTime.now().plusHours(6),
        aforoMaximo = 10,
        imagen = null,
        creador = ENTRENADOR_EJEMPLO_1,
        entrenadores = mutableListOf(ENTRENADOR_EJEMPLO_1, ENTRENADOR_EJEMPLO_2),
        empresa = EMPRESA_EJEMPLO_1
    )

    val SESION_EJEMPLO_3 = Sesion(
        id = 3,
        titulo = "Sesión de prueba 3",
        subtitulo = "Es una sesion de prueba",
        descripcion = "Es una sesion de prueba Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Donec auctor, nisl eget ultricies lacinia, nisl nunc aliquam nisl, eget aliquam nisl nunc sit amet nisl. Done",
        fechaInserccion = LocalDateTime.now(),
        fechaSesion = LocalDateTime.now().plusHours(2),
        aforoMaximo = 40,
        imagen = null,
        creador = ENTRENADOR_EJEMPLO_2,
        entrenadores = mutableListOf(ENTRENADOR_EJEMPLO_2),
        empresa = EMPRESA_EJEMPLO_1
    )

    val EMPLEADO_INSCRITO_SESION_1_EJEMPLO = EmpleadoInscribeSesion(
        id = 1,
        empleadoInscrito = EMPLEADO_EJEMPLO_1,
        sesionALaQueSeInscribe = SESION_EJEMPLO_1,
        fechaInscripcion = LocalDateTime.now()
    )

    val EMPLEADO_INSCRITO_SESION_2_EJEMPLO = EmpleadoInscribeSesion(
        id = 2,
        empleadoInscrito = EMPLEADO_EJEMPLO_1,
        sesionALaQueSeInscribe = SESION_EJEMPLO_2,
        fechaInscripcion = LocalDateTime.now()
    )

    val EMPLEADO_INSCRITO_SESION_3_EJEMPLO = EmpleadoInscribeSesion(
        id = 3,
        empleadoInscrito = EMPLEADO_EJEMPLO_1,
        sesionALaQueSeInscribe = SESION_EJEMPLO_3,
        fechaInscripcion = LocalDateTime.now()
    )

}