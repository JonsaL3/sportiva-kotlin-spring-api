package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.EmpleadoParticipaSesion
import es.dao.sportiva.model.Sesion
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpleadoInscribeSesionRepository : CrudRepository<EmpleadoInscribeSesion, Int> {

    @Query("""
        SELECT 
            NEW EmpleadoInscribeSesion(
                e.id,
                em,
                s,
                e.fechaInscripcion
            )
        FROM 
            EmpleadoInscribeSesion e 
            INNER JOIN Empleado em ON e.empleadoInscrito.id = em.id
            INNER JOIN Sesion s ON e.sesionALaQueSeInscribe.id = s.id
            INNER JOIN Entrenador en ON s.creador.id = en.id
        WHERE 
            e.sesionALaQueSeInscribe.id = ?1
    """)
    fun findBySesion(id: Int): List<EmpleadoInscribeSesion>?

    fun existsInscripcionByEmpleadoInscritoAndSesionALaQueSeInscribe(empleadoInscrito: Empleado, sesionALaQueSeInscribe: Sesion): Boolean

}
