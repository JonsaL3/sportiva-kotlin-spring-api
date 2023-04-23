package es.dao.sportiva.repository

import es.dao.sportiva.model.Sesion
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SesionRepository : CrudRepository<Sesion, Int> {

    @Query("SELECT s FROM Sesion s WHERE s.fechaSesion >= CURRENT_DATE AND s.fechaSesion < CURRENT_DATE + 1 AND s.empresa.id = ?1 AND s.isLlevadaACabo = false")
    fun findSesionesDisponibles(idEmpresa: Int): List<Sesion>

    @Query("SELECT s FROM Sesion s WHERE s.fechaSesion >= CURRENT_DATE AND s.fechaSesion < CURRENT_DATE + 1 AND s.creador.id = ?1 AND s.isLlevadaACabo = false")
    fun findSesionesDisponiblesByEntrenador(idEntrenador: Int): List<Sesion>

}