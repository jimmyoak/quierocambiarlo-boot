package es.quierocambiarlo.boot.infrastructure.messaging

import es.quierocambiarlo.boot.domain.messaging.MessageRepository
import es.quierocambiarlo.boot.domain.messaging.Offer
import kotlinx.coroutines.coroutineScope
import org.intellij.lang.annotations.Language
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.await
import org.springframework.stereotype.Repository

@Language("PostgreSQL")
private const val UPSERT_MESSAGE =
    """INSERT INTO messages(id, ad_id, type, content, sent_at)
        VALUES (:id, :adId, :type::MESSAGE_TYPE, :content, :sentAt)
        ON CONFLICT(id) DO NOTHING"""

@Repository
class R2dbcMessageRepository(
    private val database: DatabaseClient
) : MessageRepository {
    override suspend fun save(offer: Offer): Offer = coroutineScope {
        database.sql(UPSERT_MESSAGE)
            .bind("id", offer.id)
            .bind("adId", offer.adId)
            .bind("type", offer.type.name)
            .bind("content", offer.content)
            .bind("sentAt", offer.sentAt)
            .await()

        offer
    }
}
