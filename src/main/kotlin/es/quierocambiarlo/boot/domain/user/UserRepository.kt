package es.quierocambiarlo.boot.domain.user

import java.util.UUID

interface UserRepository {
    suspend fun <T : User> save(user: T): T
    suspend fun findByEmail(email: String): User?
    suspend fun findById(id: UUID): User?
}

fun User?.orNotFound(): User = this ?: throw UserNotFound()

