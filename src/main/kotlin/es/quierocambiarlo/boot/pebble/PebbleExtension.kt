package es.quierocambiarlo.boot.pebble

import com.mitchellbosecke.pebble.extension.AbstractExtension
import es.quierocambiarlo.boot.pebble.function.AdCanonicalUrlFunction
import es.quierocambiarlo.boot.pebble.function.AdUrlFunction
import es.quierocambiarlo.boot.pebble.function.SlugifyFunction
import es.quierocambiarlo.boot.pebble.function.UrlFunction
import org.springframework.stereotype.Component

@Component
object PebbleExtension : AbstractExtension() {
    private val FUNCTIONS = mapOf(
        "url" to UrlFunction,
        "slugify" to SlugifyFunction,
        "adUrl" to AdUrlFunction,
        "adCanonicalUrl" to AdCanonicalUrlFunction,
    )

    override fun getFunctions() = FUNCTIONS
}
