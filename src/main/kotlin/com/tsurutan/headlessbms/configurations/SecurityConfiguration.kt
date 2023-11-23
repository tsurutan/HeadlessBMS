package com.tsurutan.headlessbms.configurations

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationEventPublisher
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            .formLogin {
                it.loginPage("/login")
                it.successForwardUrl("/admin/articles")
            }.build()
    }

    @Bean
    @ConditionalOnMissingBean(
        UserDetailsService::class
    )
    fun inMemoryUserDetailsManager(encoder: PasswordEncoder): InMemoryUserDetailsManager {
        val generatedPassword = "password"
        return InMemoryUserDetailsManager(
            User.withUsername("user")
                .password(encoder.encode(generatedPassword)).roles("USER").build()
        );
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher::class)
    fun defaultAuthenticationEventPublisher(delegate: ApplicationEventPublisher?): DefaultAuthenticationEventPublisher {
        return DefaultAuthenticationEventPublisher(delegate)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
