package es.quierocambiarlo.boot.domain.ad

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface AdRepository {
    suspend fun save(ad: Ad): Ad

    suspend fun findAllBy(categoryId: CategoryId): Flow<Ad>

    suspend fun findById(id: UUID): Ad?

    suspend fun findAll(limit: Int = 10): Flow<Ad>
}

fun Ad?.orNotFound(): Ad = this ?: throw AdNotFound()
