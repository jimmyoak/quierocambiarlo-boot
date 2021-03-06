package es.quierocambiarlo.boot.domain.ad

import com.fasterxml.jackson.annotation.JsonProperty
import es.quierocambiarlo.boot.domain.location.Province
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.codec.multipart.FilePart
import java.time.OffsetDateTime
import java.util.UUID

data class Ad(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val categoryId: CategoryId,
    val province: Province,
    val description: String,
    val pictures: List<AdPicture>,
    val interestedOn: String,
    val advertiserId: UUID,
    val createdAt: OffsetDateTime = OffsetDateTime.now()
)

data class AdPicture(@JsonProperty("path") val path: String)

fun Flow<Ad>.toResults() = map(Ad::toResult)
fun Ad.toResult() = copy(pictures = pictures.withPath())
fun List<AdPicture>.withPath() = map { it.copy(path = "/adp/${it.path}") }
