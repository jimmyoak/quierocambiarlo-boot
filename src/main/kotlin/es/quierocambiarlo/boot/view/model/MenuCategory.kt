package es.quierocambiarlo.boot.view.model

data class MenuCategory(
    val name: String,
    val icon: String,
    val slug: String,
    val active: Boolean = false
) {
    fun activate() = if (active) this else copy(active = true)
}
