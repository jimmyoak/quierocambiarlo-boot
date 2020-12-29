package es.quierocambiarlo.boot.domain.location

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProvinceTest {
    @Test
    fun `fetch region from province`() {
        val barcelona = Province.BARCELONA

        assertThat(barcelona.label).isEqualTo("Barcelona")
        assertThat(barcelona.slug).isEqualTo("barcelona")
        assertThat(barcelona.region).isEqualTo(Region.CATALUNA)
    }

    @Test
    fun `find by term`() {
        val provinces = Province.findBy("ba")

        assertThat(provinces).containsExactly(
            Province.BADAJOZ,
            Province.BALEARES,
            Province.BARCELONA
        )
    }

    @Test
    fun `find exactly by term`() {
        val provinces = Province.findBy("barcelona")

        assertThat(provinces).containsExactly(Province.BARCELONA)
    }

    @Test
    fun `of slug`() {
        val province = Province.ofSlug("santa-cruz-de-tenerife")

        assertThat(province).isEqualTo(Province.SANTA_CRUZ_DE_TENERIFE)
    }
}
