package es.quierocambiarlo.boot.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import es.quierocambiarlo.boot.view.model.MenuCategory
import es.quierocambiarlo.boot.view.model.Seo
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class SeoConfiguration {
    @Bean
    fun categoriesSeo(
        objectMapper: ObjectMapper,
        @Value("classpath:seo/landing/categories.json") data: Resource
    ): Map<String, Seo> =
        objectMapper.readValue<List<Seo>>(data.inputStream).associateBy { it.slug }

    @Bean
    fun menuCategories(
        objectMapper: ObjectMapper,
        @Value("classpath:seo/landing/categories.json") data: Resource
    ): List<MenuCategory> =
        objectMapper.readValue<List<MenuCategory>>(data.inputStream).filter { it.visible }

}
