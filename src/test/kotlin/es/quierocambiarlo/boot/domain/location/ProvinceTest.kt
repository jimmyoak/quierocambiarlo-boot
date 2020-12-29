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
}
