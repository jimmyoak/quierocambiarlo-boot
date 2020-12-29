package es.quierocambiarlo.boot.domain.location

enum class Province(val label: String, val slug: String, val region: Region) {
    ALAVA("Álava", "alava", Region.PAIS_VASCO),
    ALBACETE("Albacete", "albacete", Region.CASTILLA_LA_MANCHA),
    ALICANTE("Alicante", "alicante", Region.COMUNIDAD_VALENCIANA),
    ALMERIA("Almería", "almeria", Region.ANDALUCIA),
    ASTURIAS("Asturias", "asturias", Region.PRINCIPADO_DE_ASTURIAS),
    AVILA("Ávila", "avila", Region.CASTILLA_Y_LEON),
    A_CORUNA("A Coruña", "a-coruna", Region.GALICIA),
    BADAJOZ("Badajoz", "badajoz", Region.EXTREMADURA),
    BALEARES("Baleares", "baleares", Region.ISLAS_BALEARES),
    BARCELONA("Barcelona", "barcelona", Region.CATALUNA),
    BURGOS("Burgos", "burgos", Region.CASTILLA_Y_LEON),
    CACERES("Cáceres", "caceres", Region.EXTREMADURA),
    CADIZ("Cádiz", "cadiz", Region.ANDALUCIA),
    CANTABRIA("Cantabria", "cantabria", Region.CANTABRIA),
    CASTELLON("Castellón", "castellon", Region.COMUNIDAD_VALENCIANA),
    CEUTA("Ceuta", "ceuta", Region.CEUTA),
    CIUDAD_REAL("Ciudad Real", "ciudad-real", Region.CASTILLA_LA_MANCHA),
    CORDOBA("Córdoba", "cordoba", Region.ANDALUCIA),
    CUENCA("Cuenca", "cuenca", Region.CASTILLA_LA_MANCHA),
    GIPUZKOA("Gipuzkoa", "gipuzkoa", Region.PAIS_VASCO),
    GIRONA("Girona", "girona", Region.CATALUNA),
    GRANADA("Granada", "granada", Region.ANDALUCIA),
    GUADALAJARA("Guadalajara", "guadalajara", Region.CASTILLA_LA_MANCHA),
    HUELVA("Huelva", "huelva", Region.ANDALUCIA),
    HUESCA("Huesca", "huesca", Region.ARAGON),
    JAEN("Jaén", "jaen", Region.ANDALUCIA),
    LAS_PALMAS("Las Palmas", "las-palmas", Region.ISLAS_BALEARES),
    LA_RIOJA("La Rioja", "la-rioja", Region.LA_RIOJA),
    LEON("León", "leon", Region.CASTILLA_Y_LEON),
    LERIDA("Lleida", "lerida", Region.CATALUNA),
    LUGO("Lugo", "lugo", Region.GALICIA),
    MADRID("Madrid", "madrid", Region.COMUNIDAD_DE_MADRID),
    MALAGA("Málaga", "malaga", Region.ANDALUCIA),
    MELILLA("Melilla", "melilla", Region.MELILLA),
    MURCIA("Murcia", "murcia", Region.REGION_DE_MURCIA),
    NAVARRA("Navarra", "navarra", Region.COMUNIDAD_FORAL_DE_NAVARRA),
    OURENSE("Ourense", "ourense", Region.GALICIA),
    PALENCIA("Palencia", "palencia", Region.CASTILLA_Y_LEON),
    PONTEVEDRA("Pontevedra", "pontevedra", Region.GALICIA),
    SALAMANCA("Salamanca", "salamanca", Region.CASTILLA_Y_LEON),
    SANTA_CRUZ_DE_TENERIFE("Santa Cruz de Tenerife", "santa-cruz-de-tenerife", Region.CANARIAS),
    SEGOVIA("Segovia", "segovia", Region.CASTILLA_Y_LEON),
    SEVILLA("Sevilla", "sevilla", Region.ANDALUCIA),
    SORIA("Soria", "soria", Region.CASTILLA_Y_LEON),
    TARRAGONA("Tarragona", "tarragona", Region.CATALUNA),
    TERUEL("Teruel", "teruel", Region.ARAGON),
    TOLEDO("Toledo", "toledo", Region.CASTILLA_LA_MANCHA),
    VALENCIA("Valencia", "valencia", Region.COMUNIDAD_VALENCIANA),
    VALLADOLID("Valladolid", "valladolid", Region.CASTILLA_Y_LEON),
    VIZCAYA("Vizcaya", "vizcaya", Region.PAIS_VASCO),
    ZAMORA("Zamora", "zamora", Region.CASTILLA_Y_LEON),
    ZARAGOZA("Zaragoza", "zaragoza", Region.ARAGON),
    ;

    companion object {
        fun findBy(term: String): List<Province> =
            (listOfNotNull(ofLabel(term)) + byTerm(term)).distinct()

        fun ofSlug(slug: String): Province? = values().firstOrNull { it.slug.equals(slug, ignoreCase = true) }

        private fun ofLabel(label: String): Province? = values().firstOrNull { it.label.equals(label, ignoreCase = true) }

        private fun byTerm(term: String): List<Province> = values().filter { it.label.startsWith(term, ignoreCase = true) }
    }
}
