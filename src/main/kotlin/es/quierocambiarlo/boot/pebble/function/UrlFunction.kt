package es.quierocambiarlo.boot.pebble.function

import com.mitchellbosecke.pebble.extension.Function
import com.mitchellbosecke.pebble.template.EvaluationContext
import com.mitchellbosecke.pebble.template.PebbleTemplate

object UrlFunction : Function {
    private const val BASE_PATH = "https://www.quierocambiarlo.es"
    private const val PATH_ARG = "path"

    private val ARGUMENT_NAMES = listOf(PATH_ARG)

    override fun getArgumentNames() = ARGUMENT_NAMES

    override fun execute(
        args: Map<String, Any>,
        self: PebbleTemplate,
        context: EvaluationContext,
        lineNumber: Int
    ): Any {
        return BASE_PATH + path(args)
    }

    private fun path(args: Map<String, Any>): String = (args[PATH_ARG] as String?) ?: "/"
}
