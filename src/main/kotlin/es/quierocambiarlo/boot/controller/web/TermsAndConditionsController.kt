package es.quierocambiarlo.boot.controller.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/terminos-y-condiciones")
@Controller
class TermsAndConditionsController {
    @GetMapping
    suspend fun termsAndConditions() = "legal/terms_and_conditions"
}
