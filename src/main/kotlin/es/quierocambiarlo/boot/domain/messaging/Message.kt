package es.quierocambiarlo.boot.domain.messaging

import java.time.OffsetDateTime
import java.util.UUID

sealed class Message {
    abstract val id: UUID
    abstract val adId: UUID
    abstract val type: MessageType
    abstract val sentAt: OffsetDateTime
}

enum class MessageType {
    OFFER,
}

data class Offer(
    override val id: UUID = UUID.randomUUID(),
    override val adId: UUID,
    val content: String,
    override val sentAt: OffsetDateTime = OffsetDateTime.now()
) : Message() {
    override val type = MessageType.OFFER
}
