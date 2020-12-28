package es.quierocambiarlo.boot.pebble

import com.mitchellbosecke.pebble.extension.AbstractExtension
import es.quierocambiarlo.boot.pebble.function.UrlFunction
import org.springframework.stereotype.Component

@Component
object PebbleExtension : AbstractExtension() {
    private val FUNCTIONS = mapOf("url" to UrlFunction)

    override fun getFunctions() = FUNCTIONS
}
