package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.EmpleadoInscribeSesion
import es.dao.sportiva.model.EmpleadoParticipaSesion
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpleadoInscribeSesionRepository : CrudRepository<EmpleadoInscribeSesion, Int> {

    @Query("SELECT e FROM EmpleadoInscribeSesion e WHERE e.sesionALaQueSeInscribe.id = ?1")
    fun findBySesion(id: Int): List<EmpleadoInscribeSesion>?

}
