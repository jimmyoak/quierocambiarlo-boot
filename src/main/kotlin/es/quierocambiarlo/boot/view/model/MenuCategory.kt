package es.quierocambiarlo.boot.view.model

data class MenuCategory(
    val id: String,
    val name: String,
    val icon: String,
    val slug: String,
    val visible: Boolean = true,
    val active: Boolean = false
) {
    fun activate() = if (active) this else copy(active = true)
}
