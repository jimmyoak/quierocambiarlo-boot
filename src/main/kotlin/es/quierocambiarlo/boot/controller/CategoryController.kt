package es.quierocambiarlo.boot.controller

import es.quierocambiarlo.boot.view.model.MenuCategory
import es.quierocambiarlo.boot.view.model.Seo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class CategoryController(
    private val categoriesSeo: Map<String, Seo>,
    private val menuCategories: List<MenuCategory>
) {
    @GetMapping("/trueques-de-{slug:[\\w-]+}.html")
    fun landing(@PathVariable slug: String, model: Model): String {
        val seo = categoriesSeo[slug] ?: return "forward:notfoundpage"

        model["seo"] = seo
        model["openGraph"] = seo.toOpenGraph()
        model["menuCategories"] = menuCategoriesWithActive(slug)

        return "landing/category/index"
    }

    private fun menuCategoriesWithActive(slug: String) = menuCategories.map {
        if (it.slug == slug) it.activate() else it
    }
}