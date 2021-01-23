package es.quierocambiarlo.boot.infrastructure.r2dbc

import io.r2dbc.spi.Row

internal inline fun <reified T> Row.nonNull(name: String): T = this.get(name, T::class.java)!!
internal inline fun <reified T> Row.nullable(name: String): T? = this.get(name, T::class.java)
