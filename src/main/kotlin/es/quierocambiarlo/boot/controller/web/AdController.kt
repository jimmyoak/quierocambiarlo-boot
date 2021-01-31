package es.quierocambiarlo.boot.controller.web

import es.quierocambiarlo.boot.domain.ad.AdRepository
import es.quierocambiarlo.boot.domain.ad.orNotFound
import es.quierocambiarlo.boot.domain.user.UserRepository
import es.quierocambiarlo.boot.domain.user.orNotFound
import es.quierocambiarlo.boot.infrastructure.logger.logger
import es.quierocambiarlo.boot.view.model.MenuCategory
import es.quierocambiarlo.boot.view.model.Seo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

private val LOGGER = logger<AdController>()

@Controller
class AdController(
    private val adRepository: AdRepository,
    private val userRepository: UserRepository,
    private val menuCategories: List<MenuCategory>
) {
    @GetMapping("/cambio-{slug:[\\w-]+}/{adId:[\\w-]+}")
    suspend fun page(
        @PathVariable slug: String,
        @PathVariable adId: UUID,
        model: Model
    ): String {
        LOGGER.info("Fetching $slug: $adId")
        val ad = adRepository.findById(adId).orNotFound().let{ ad ->
            ad.copy(pictures = ad.pictures.map { it.copy(path = "/adp/${it.path}") })
        }
        val category = menuCategories.firstOrNull { it.id == ad.categoryId.name } ?: throw CategorySlugNotFoundException()
        val user = userRepository.findById(ad.advertiserId).orNotFound()

        model["ad"] = ad
        model["user"] = user
        model["category"] = category

        return "ad/page/index"
    }
}
