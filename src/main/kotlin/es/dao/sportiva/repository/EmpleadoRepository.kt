package es.dao.sportiva.repository

import es.dao.sportiva.model.Empleado
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository

interface EmpleadoRepository : CrudRepository<Empleado, Int> {

    fun findByCorreo(correo: String): Empleado

    @Modifying
    @Query("UPDATE Empleado e SET e.imagen = :imagen WHERE e.id = :id")
    fun updateImagenById(@Param("id") id: Int?, @Param("imagen") imagen: String?): Int
    
}