package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpleadoRepository : CrudRepository<Empleado, Int> {

    fun findByCorreo(correo: String): Empleado
    
}