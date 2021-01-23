package es.quierocambiarlo.boot.infrastructure.user

import es.quierocambiarlo.boot.domain.user.User
import es.quierocambiarlo.boot.domain.user.UserRepository
import es.quierocambiarlo.boot.infrastructure.r2dbc.nonNull
import es.quierocambiarlo.boot.infrastructure.r2dbc.nullable
import io.r2dbc.spi.Row
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.intellij.lang.annotations.Language
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitSingleOrNull
import org.springframework.r2dbc.core.bind
import org.springframework.stereotype.Repository

@Language("PostgreSQL")
private const val SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = :email"

@Language("PostgreSQL")
private const val UPSERT_USER =
    """INSERT INTO users(id, name, email, phone, created_at) VALUES (:id, :name, :email, :phone, :createdAt)
ON CONFLICT(id) DO UPDATE SET name = :name, email = :email, phone = :phone, created_at = :createdAt"""

@Repository
class R2dbcUserRepository(private val database: DatabaseClient) : UserRepository {
    override suspend fun <T : User> save(user: T): T = coroutineScope {
        when (user) {
            is User.NonRegistered -> save(user)
        }
        user
    }

    override suspend fun findByEmail(email: String): User? =
        database.sql(SELECT_BY_EMAIL)
            .bind("email", email)
            .map { row ->
                User.NonRegistered(
                    row.nonNull("id"),
                    row.nonNull("name"),
                    row.nonNull("email"),
                    row.nullable("phone"),
                    row.nonNull("created_at")
                )
            }
            .awaitSingleOrNull()

    suspend fun save(user: User.NonRegistered) {
        database.sql(UPSERT_USER)
            .bind("id", user.id)
            .bind("name", user.name)
            .bind("email", user.email)
            .bind("phone", user.phone)
            .bind("createdAt", user.createdAt)
            .then()
            .awaitSingleOrNull()
    }
}

