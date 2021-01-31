package es.quierocambiarlo.boot.pebble.function

import com.mitchellbosecke.pebble.extension.Function
import com.mitchellbosecke.pebble.template.EvaluationContext
import com.mitchellbosecke.pebble.template.PebbleTemplate

object SlugifyFunction : Function {
    private const val TEXT_ARG = "text"
    private val ARGUMENT_NAMES = listOf(TEXT_ARG)

    override fun getArgumentNames() = ARGUMENT_NAMES

    override fun execute(
        args: Map<String, Any>,
        self: PebbleTemplate,
        context: EvaluationContext,
        lineNumber: Int
    ) = text(args).toSlug()

    private fun text(args: Map<String, Any>) = (args[TEXT_ARG] as String?).orEmpty()
}
