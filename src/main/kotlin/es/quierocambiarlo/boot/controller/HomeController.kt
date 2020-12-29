package es.quierocambiarlo.boot.controller

import es.quierocambiarlo.boot.view.model.MenuCategory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val menuCategories: List<MenuCategory>
) {
    @GetMapping("/")
    suspend fun home(model: Model): String {
        model["menuCategories"] = menuCategories

        return "home/index"
    }
}
