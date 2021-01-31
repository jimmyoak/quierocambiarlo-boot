package es.quierocambiarlo.boot.infrastructure.ad

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import es.quierocambiarlo.boot.domain.ad.Ad
import es.quierocambiarlo.boot.domain.ad.AdRepository
import es.quierocambiarlo.boot.domain.ad.CategoryId
import es.quierocambiarlo.boot.domain.location.Province
import es.quierocambiarlo.boot.infrastructure.r2dbc.nonNull
import io.r2dbc.spi.Row
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.intellij.lang.annotations.Language
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitSingleOrNull
import org.springframework.stereotype.Repository
import java.util.UUID

@Language("PostgreSQL")
private const val UPSERT_AD =
    """INSERT INTO ads(id, title, category, province, description, interested_on, pictures, user_id, created_at)
VALUES(:id, :title, :category::AD_CATEGORY, :province::PROVINCE, :description, :interestedOn, :pictures::JSON, :userId, :createdAt)
ON CONFLICT(id) DO UPDATE SET id = :id, title = :title, category = :category::AD_CATEGORY, province = :province::PROVINCE, description = :description, interested_on = :interestedOn, pictures = :pictures::JSON, user_id = :userId, created_at = :createdAt"""

@Language("PostgreSQL")
private const val SELECT_ALL_BY_CATEGORY_ID =
    """SELECT id,
       id,
       title,
       category::VARCHAR,
       province::VARCHAR,
       description,
       interested_on,
       pictures,
       user_id,
       created_at
FROM ads
WHERE category = :category::AD_CATEGORY
ORDER BY created_at DESC"""

@Language("PostgreSQL")
private const val SELECT_ALL_BY_ID =
    """SELECT id,
       id,
       title,
       category::VARCHAR,
       province::VARCHAR,
       description,
       interested_on,
       pictures,
       user_id,
       created_at
FROM ads
WHERE id = :id
ORDER BY created_at DESC"""

@Repository
class ReactiveAdRepository(
    private val database: DatabaseClient,
    private val objectMapper: ObjectMapper
) : AdRepository {
    override suspend fun save(ad: Ad): Ad = coroutineScope {
        database.sql(UPSERT_AD)
            .bind("id", ad.id)
            .bind("title", ad.title)
            .bind("category", ad.categoryId.name)
            .bind("province", ad.province.name)
            .bind("description", ad.description)
            .bind("interestedOn", ad.interestedOn)
            .bind("pictures", objectMapper.writeValueAsString(ad.pictures))
            .bind("userId", ad.advertiserId)
            .bind("createdAt", ad.createdAt)
            .then()
            .awaitSingleOrNull()

        ad
    }

    override suspend fun findAllBy(categoryId: CategoryId): Flow<Ad> = coroutineScope {
        database.sql(SELECT_ALL_BY_CATEGORY_ID)
            .bind("category", categoryId.name)
            .map(::createAd)
            .all()
            .asFlow()
    }

    override suspend fun findById(id: UUID): Ad? = coroutineScope {
        database.sql(SELECT_ALL_BY_ID)
            .bind("id", id)
            .map(::createAd)
            .awaitSingleOrNull()
    }

    private fun createAd(row: Row): Ad =
        Ad(
            row.nonNull("id"),
            row.nonNull("title"),
            row.nonNull<String>("category").let(CategoryId::valueOf),
            row.nonNull<String>("province").let(Province::valueOf),
            row.nonNull("description"),
            objectMapper.readValue(row.nonNull<String>("pictures")),
            row.nonNull("interested_on"),
            row.nonNull("user_id"),
            row.nonNull("created_at")
        )
}
