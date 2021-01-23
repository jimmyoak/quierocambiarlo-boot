package es.quierocambiarlo.boot.domain.ad

import kotlinx.coroutines.flow.Flow

interface AdRepository {
    suspend fun save(ad: Ad): Ad

    suspend fun findAllBy(categoryId: CategoryId): Flow<Ad>
}
