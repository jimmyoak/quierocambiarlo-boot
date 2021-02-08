package es.quierocambiarlo.boot.domain.messaging

interface MessageRepository {
    suspend fun save(offer: Offer): Offer
}
