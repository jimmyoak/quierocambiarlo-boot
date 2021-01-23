package es.quierocambiarlo.boot.domain.user

import java.time.OffsetDateTime
import java.util.UUID

sealed class User {
    abstract val id: UUID

    data class NonRegistered(
        override val id: UUID = UUID.randomUUID(),
        val name: String,
        val email: String,
        val phone: String?,
        val createdAt: OffsetDateTime = OffsetDateTime.now()
    ) : User()
}
