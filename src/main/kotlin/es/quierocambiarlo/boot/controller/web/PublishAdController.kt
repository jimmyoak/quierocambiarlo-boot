package es.quierocambiarlo.boot.controller.web

import es.quierocambiarlo.boot.domain.location.Province
import es.quierocambiarlo.boot.view.model.MenuCategory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PublishAdController(private val menuCategories: List<MenuCategory>) {
    @GetMapping("/publicar-anuncio.html")
    fun publishAd(): String = "publishAd/new"

    @GetMapping("/publicar-anuncio")
    fun publishAdForm(model: Model): String {
        model["categories"] = menuCategories
        model["provinces"] = Province.values()

        return "publishAd/new"
    }
}
