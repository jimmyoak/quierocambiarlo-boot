package es.quierocambiarlo.boot.url

import es.quierocambiarlo.boot.domain.location.Province
import es.quierocambiarlo.boot.pebble.function.toSlug
import java.util.UUID

object AdPageUrlGenerator {
    fun full(adId: UUID, title: String, province: Province, interestedOn: String): String =
        DomainUrlGenerator.full(relative(adId, title, province, interestedOn))

    fun relative(adId: UUID, title: String, province: Province, interestedOn: String): String =
        "/cambio-${title.toSlug()}-por-${interestedOn.toSlug()}-en-${province.label.toSlug()}/${adId}"
}
