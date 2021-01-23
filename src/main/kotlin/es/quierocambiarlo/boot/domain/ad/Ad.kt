package es.quierocambiarlo.boot.domain.ad

import es.quierocambiarlo.boot.domain.location.Province
import org.springframework.http.codec.multipart.FilePart
import java.time.OffsetDateTime
import java.util.UUID

class Ad(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val categoryId: CategoryId,
    val province: Province,
    val description: String,
    val pictures: List<FilePart>,
    val interestedOn: String,
    val advertiserId: UUID,
    val createdAt: OffsetDateTime = OffsetDateTime.now()
)
