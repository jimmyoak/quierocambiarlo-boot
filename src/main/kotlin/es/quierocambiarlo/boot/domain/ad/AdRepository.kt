package es.quierocambiarlo.boot.domain.ad

interface AdRepository {
    suspend fun save(ad: Ad): Ad
}
