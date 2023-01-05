package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.model.Empresa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : CrudRepository<Empresa, Int> {
}