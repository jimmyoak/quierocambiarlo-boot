package es.quierocambiarlo.boot.domain.location

enum class Region(val label: String, val slug: String) {
    ANDALUCIA("Andalucía", "andalucia"),
    ARAGON("Aragón", "aragon"),
    CANARIAS("Canarias", "canarias"),
    CANTABRIA("Cantabria", "cantabria"),
    CASTILLA_LA_MANCHA("Castilla-La Mancha", "castilla-la-mancha"),
    CASTILLA_Y_LEON("Castilla y León", "castilla-y-leon"),
    CATALUNA("Cataluña", "cataluna"),
    CEUTA("Ceuta", "ceuta"),
    COMUNIDAD_DE_MADRID("Madrid", "madrid"),
    COMUNIDAD_FORAL_DE_NAVARRA("Navarra", "navarra"),
    COMUNIDAD_VALENCIANA("Comunidad Valenciana", "comunidad-valenciana"),
    EXTREMADURA("Extremadura", "extremadura"),
    GALICIA("Galicia", "galicia"),
    ISLAS_BALEARES("Islas Baleares", "islas-baleares"),
    LA_RIOJA("La Rioja", "la-rioja"),
    MELILLA("Melilla", "melilla"),
    PAIS_VASCO("País Vasco", "pais-vasco"),
    PRINCIPADO_DE_ASTURIAS("Asturias", "asturias"),
    REGION_DE_MURCIA("Murcia", "murcia"),
    ;

    val provinces: List<Province>
        get() = Province.values().filter { it.region == this }
}
