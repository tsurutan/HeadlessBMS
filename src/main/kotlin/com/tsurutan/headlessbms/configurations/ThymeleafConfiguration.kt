package com.tsurutan.headlessbms.configurations

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ThymeleafConfiguration {
    @Bean
    fun layoutDialect(): LayoutDialect {
        return LayoutDialect()
    }
}
