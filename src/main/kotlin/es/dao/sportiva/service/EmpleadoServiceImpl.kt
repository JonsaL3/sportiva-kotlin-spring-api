package es.dao.sportiva.service

import es.dao.sportiva.model.Empleado
import es.dao.sportiva.repository.EmpleadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmpleadoServiceImpl: IEmpleadoService {

    @Autowired lateinit var repo: EmpleadoRepository

    override fun insert(empleado: Empleado): Boolean {
        var exito = false
        try {
            repo.save(empleado)
            exito = true
        } catch (e: Exception) {
            println("\nError al insertar empleado: ${e.message}\n")
        }
        return exito
    }

    override fun update(empleado: Empleado): Boolean {
        var exito = false
        try {
            repo.save(empleado)
            exito = true
        } catch (e: Exception) {
            println("\nError al actualizar empleado: ${e.message}\n")
        }
        return exito
    }

    override fun delete(id: Int): Boolean {
        var exito = false
        try {
            repo.deleteById(id)
            exito = true
        } catch (e: Exception) {
            println("\nError al borrar empleado: ${e.message}\n")
        }
        return exito
    }

    override fun findAll(): List<Empleado>? {
        var lista: List<Empleado>? = null
        try {
            lista = repo.findAll().toList()
        } catch (e: Exception) {
            println("\nError al buscar todos los empleados: ${e.message}\n")
        }
        return lista
    }

    override fun findById(id: Int): Empleado? {
        var empleado: Empleado? = null
        try {
            empleado = repo.findById(id).orElse(null)
        } catch (e: Exception) {
            println("\nError al buscar empleado por id: ${e.message}\n")
        }
        return empleado
    }

    override fun findByCorreo(correo: String): Empleado? {
        var empleado: Empleado? = null
        try {
            empleado = repo.findByCorreo(correo)
        } catch (e: Exception) {
            println("\nError al buscar empleado por correo: ${e.message}\n")
        }
        return empleado
    }

}