package es.quierocambiarlo.boot.controller

import es.quierocambiarlo.boot.domain.location.Province
import es.quierocambiarlo.boot.view.model.MenuCategory
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

private val LOGGER = LoggerFactory.getLogger(PublishAdController::class.java)

@Controller
class PublishAdController(
    private val menuCategories: List<MenuCategory>
) {
    @GetMapping("/publicar-anuncio.html")
    fun publishAd(): String = "publishAd/index"

    @GetMapping("/publicar-anuncio")
    fun publishAdForm(model: Model): String {
        model["categories"] = menuCategories
        model["provinces"] = Province.values()

        return "publishAd/new"
    }

    @PostMapping("/publicar-anuncio", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun publish(@ModelAttribute formData: PublishAdFormData): ResponseEntity<Void> {
        formData.pictures.forEach {
            LOGGER.info("${it.filename()}: ${it.name()}")
        }

        return ResponseEntity.ok().build()
    }
}

class PublishAdFormData(
    val title: String,
    val category: String,
    val province: String,
    val description: String,
    val pictures: List<FilePart>,
    val interested_on: String,
    val advertiser_name: String,
    val advertiser_email: String,
    val advertiser_phone: String?
)
