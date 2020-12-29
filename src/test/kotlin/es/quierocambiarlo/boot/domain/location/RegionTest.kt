package es.quierocambiarlo.boot.domain.location

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RegionTest {
    @Test
    fun `fetch provinces from region`() {
        val cataluna = Region.CATALUNA

        assertThat(cataluna.label).isEqualTo("Cataluña")
        assertThat(cataluna.slug).isEqualTo("cataluna")
        assertThat(cataluna.provinces).containsExactly(
            Province.BARCELONA,
            Province.GIRONA,
            Province.LERIDA,
            Province.TARRAGONA
        )
    }
}
