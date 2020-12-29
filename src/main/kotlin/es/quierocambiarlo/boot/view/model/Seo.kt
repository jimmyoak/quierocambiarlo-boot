package es.quierocambiarlo.boot.view.model

import es.quierocambiarlo.boot.url.DomainUrlGenerator

data class Seo(
    val slug: String,
    val title: String,
    val h1: String,
    val h2: String,
    val description: String,
    val keywords: List<String>
) {
    fun toOpenGraph(): OpenGraph = OpenGraph(
        title,
        description,
        canonical
    )

    val canonical: String
        get() = DomainUrlGenerator.full("/trueques-de-$slug.html")
}
