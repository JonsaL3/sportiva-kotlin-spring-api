package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Entrenador
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EntrenadorRepository : CrudRepository<Entrenador, Int> {

    fun findByCorreo(correo: String): Entrenador

    @Query("SELECT e FROM Entrenador e WHERE e.empresaAsignada.id = ?1 AND e.isActivo = 1")
    fun findByIdEmpresaAsignada(idEmpresa: Int): List<Entrenador>

}