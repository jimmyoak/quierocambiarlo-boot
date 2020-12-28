package es.quierocambiarlo.boot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping("/")
    suspend fun home(): String = "home/index"
}
