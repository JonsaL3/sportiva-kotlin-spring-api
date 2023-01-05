package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.EmpleadoParticipaSesion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpleadoParticipaSesionRepository : CrudRepository<EmpleadoParticipaSesion, Int> {
}