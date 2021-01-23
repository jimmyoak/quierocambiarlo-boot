package es.quierocambiarlo.boot.configuration

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfiguration {
    @Bean(initMethod = "migrate")
    fun flyway(connection: R2dbcProperties): Flyway =
        Flyway.configure()
            .baselineOnMigrate(false)
            .dataSource(connection.url.replace("r2dbc", "jdbc"), connection.username, connection.password)
            .load()
}
