package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Empresa
import es.dao.sportiva.model.Version
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VersionRepository : CrudRepository<Version, Int> {
}