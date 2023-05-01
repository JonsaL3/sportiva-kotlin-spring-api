package es.dao.sportiva.controller

import es.dao.sportiva.model.Version
import es.dao.sportiva.requests.IniciarSesionRequest
import es.dao.sportiva.service.VersionServiceImpl
import es.dao.sportiva.utils.Constantes
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@RestController
@RequestMapping("/api/version")
class VersionController {

    @Autowired
    lateinit var service: VersionServiceImpl

    @GetMapping("/downloadCurrentApk")
    fun downloadCurrentApk(): ResponseEntity<Resource> {

        val apkFile = File("sportiva.apk")
        val path = Paths.get(apkFile.absolutePath)
        val byteArrayResource = ByteArrayResource(Files.readAllBytes(path))

        return ResponseEntity.ok()
            .contentLength(apkFile.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(byteArrayResource);
    }

    // Inseguro de cojones, pero como esto no es para el gran público... si asi fuese habría que usar tokens etc etc...
    @PostMapping("/setNewVersion")
    fun setNewVersion(@RequestBody request: Version): ResponseEntity<String> {

        return if (service.findAll()?.any { v -> v.versionInt == request.versionInt } == true) {
            val headers = HttpHeaders()
            headers.add(Constantes.HEADER_ERROR_MESSAGE, "Estas tratando de publicar una versión que ya existe.")
            ResponseEntity.badRequest().headers(headers).build()
        } else {
            service.insert(request)
            ResponseEntity.ok().body("Versión publicada correctamente.")
        }

    }

    @GetMapping("/getLatestVersion")
    fun getLatestVersion(): ResponseEntity<Version> {

        val version = if (service.findAll().isNullOrEmpty()) {
            Optional.empty<Version>()
        } else {
            // max Version object where versionInt is max
            service.findAll()!!.stream().max { o1, o2 -> o1.versionInt.compareTo(o2.versionInt) }
        }

        return if (version.isPresent) {
            ResponseEntity.ok().body(version.get())
        } else {
            val headers = HttpHeaders()
            headers.add(Constantes.HEADER_ERROR_MESSAGE, "No hay ninguna versión publicada aún, avisar a Gonzalo :D")
            ResponseEntity.notFound().headers(headers).build()
        }
    }

}