package es.quierocambiarlo.boot.url

import org.springframework.stereotype.Component

interface UrlGenerator {
    fun full(path: String): String
}

@Component
object DomainUrlGenerator : UrlGenerator {
    private const val BASE_PATH = "https://www.quierocambiarlo.es"

    override fun full(path: String) = BASE_PATH + path.withPrecedingSlash()

    private fun String.withPrecedingSlash(): String = when {
        isEmpty() -> "/"
        first() != '/' -> "/$this"
        else -> this
    }
}
