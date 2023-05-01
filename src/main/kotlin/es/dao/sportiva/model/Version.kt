package es.dao.sportiva.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Version(
    @Id
    var versionInt: Int = 0
)