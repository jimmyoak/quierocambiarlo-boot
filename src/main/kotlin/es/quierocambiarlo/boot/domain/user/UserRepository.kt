package es.quierocambiarlo.boot.domain.user

interface UserRepository {
    suspend fun <T : User> save(user: T): T
    suspend fun findByEmail(email: String): User?
}
