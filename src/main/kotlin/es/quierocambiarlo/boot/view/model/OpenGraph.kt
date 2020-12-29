package es.quierocambiarlo.boot.view.model

import es.quierocambiarlo.boot.url.DomainUrlGenerator

data class OpenGraph(
    val title: String,
    val description: String,
    val url: String,
    val image: String = DomainUrlGenerator.full("/logo.png"),
    val type: String = "website"
)
