package es.quierocambiarlo.boot.pebble.function

import com.mitchellbosecke.pebble.extension.Function
import com.mitchellbosecke.pebble.template.EvaluationContext
import com.mitchellbosecke.pebble.template.PebbleTemplate
import es.quierocambiarlo.boot.url.AdPageUrlGenerator

object AdUrlFunction : Function {
    private const val AD_ID_ARG = "adId"
    private const val TITLE_ARG = "title"
    private const val PROVINCE_ARG = "province"
    private const val INTERESTED_ON_ARG = "interestedOn"
    private val ARGUMENT_NAMES = listOf(AD_ID_ARG, TITLE_ARG, PROVINCE_ARG, INTERESTED_ON_ARG)

    override fun getArgumentNames() = ARGUMENT_NAMES

    override fun execute(
        args: Map<String, Any>,
        self: PebbleTemplate,
        context: EvaluationContext,
        lineNumber: Int
    ) = AdPageUrlGenerator.relative(
        args.fetch(AD_ID_ARG),
        args.fetch(TITLE_ARG),
        args.fetch(PROVINCE_ARG),
        args.fetch(INTERESTED_ON_ARG)
    )

    private inline fun <reified T> Map<String, Any>.fetch(name: String): T =
        (this[name] as T)
}
