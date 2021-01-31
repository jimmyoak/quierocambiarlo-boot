package es.quierocambiarlo.boot.pebble.function

import com.github.slugify.Slugify

internal val SLUGIFY = Slugify()
internal fun String.toSlug(): String = SLUGIFY.slugify(this)
