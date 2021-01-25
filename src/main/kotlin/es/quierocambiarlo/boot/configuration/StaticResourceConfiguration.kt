package es.quierocambiarlo.boot.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.util.concurrent.TimeUnit

@Configuration
class StaticResourceConfiguration : WebFluxConfigurer {
    @Value("\${application.ads.pictures.path}")
    private lateinit var adPicturesDirectory: String

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/adp/**")
            .addResourceLocations("file:$adPicturesDirectory")
            .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
            .resourceChain(true)
    }
}
