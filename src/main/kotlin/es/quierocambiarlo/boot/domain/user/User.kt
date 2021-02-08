package es.quierocambiarlo.boot.domain.user

import es.quierocambiarlo.boot.domain.ad.Ad
import es.quierocambiarlo.boot.domain.messaging.Offer
import java.time.OffsetDateTime
import java.util.UUID

sealed class User {
    abstract val id: UUID

    fun offersOn(ad: Ad, content: String): Offer = Offer(adId = ad.id, content = content)

    data class NonRegistered(
        override val id: UUID = UUID.randomUUID(),
        val name: String,
        val email: String,
        val phone: String? = null,
        val createdAt: OffsetDateTime = OffsetDateTime.now()
    ) : User()
}
