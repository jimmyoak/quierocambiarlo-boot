package es.quierocambiarlo.boot.controller.web

import es.quierocambiarlo.boot.domain.ad.AdRepository
import es.quierocambiarlo.boot.domain.ad.toResults
import es.quierocambiarlo.boot.view.model.MenuCategory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

private const val LAST_ADS_LIMIT = 5

@Controller
class HomeController(
    private val menuCategories: List<MenuCategory>,
    private val adRepository: AdRepository
) {
    @GetMapping("/")
    suspend fun home(model: Model): String {
        model["menuCategories"] = menuCategories
        model["lastAds"] = adRepository.findAll(LAST_ADS_LIMIT).toResults()

        return "home/index"
    }
}
