package es.quierocambiarlo.boot.infrastructure.ad

import es.quierocambiarlo.boot.domain.ad.Ad
import es.quierocambiarlo.boot.domain.ad.AdRepository
import es.quierocambiarlo.boot.domain.ad.CategoryId
import es.quierocambiarlo.boot.infrastructure.r2dbc.nonNull
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingleOrNull
import org.intellij.lang.annotations.Language
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Language("PostgreSQL")
private const val UPSERT_AD =
    """INSERT INTO ads(id, title, category, province, description, interested_on, pictures, user_id, created_at)
VALUES(:id, :title, :category::AD_CATEGORY, :province::PROVINCE, :description, :interestedOn, :pictures::JSON, :userId, :createdAt)
ON CONFLICT(id) DO UPDATE SET id = :id, title = :title, category = :category::AD_CATEGORY, province = :province::PROVINCE, description = :description, interested_on = :interestedOn, pictures = :pictures::JSON, user_id = :userId, created_at = :createdAt"""

@Language("PostgreSQL")
private const val SELECT_ALL_BY_CATEGORY_ID =
    """SELECT * FROM ads WHERE category = :category"""

@Repository
class ReactiveAdRepository(val database: DatabaseClient) : AdRepository {
    override suspend fun save(ad: Ad): Ad = coroutineScope {
        database.sql(UPSERT_AD)
            .bind("id", ad.id)
            .bind("title", ad.title)
            .bind("category", ad.categoryId.name)
            .bind("province", ad.province.name)
            .bind("description", ad.description)
            .bind("interestedOn", ad.interestedOn)
            .bind("pictures", "[]")
            .bind("userId", ad.advertiserId)
            .bind("createdAt", ad.createdAt)
            .then()
            .awaitSingleOrNull()

        ad
    }

    override suspend fun findAllBy(categoryId: CategoryId): Flow<Ad> = coroutineScope {
        database.sql(SELECT_ALL_BY_CATEGORY_ID)
            .bind("category", categoryId.name)
            .map { row ->
                Ad(
                    row.nonNull("id"),
                    row.nonNull("title"),
                    row.nonNull("category"),
                    row.nonNull("province"),
                    row.nonNull("description"),
                    emptyList(),
                    row.nonNull("interested_on"),
                    row.nonNull("user_id"),
                    row.nonNull("created_at")
                )
            }
            .all()
            .asFlow()
    }
}
