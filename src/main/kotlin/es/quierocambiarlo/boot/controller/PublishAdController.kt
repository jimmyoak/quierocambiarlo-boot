package es.quierocambiarlo.boot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PublishAdController {
    @GetMapping("/publicar-anuncio.html")
    fun publishAd(): String = "publishAd/index"
}
