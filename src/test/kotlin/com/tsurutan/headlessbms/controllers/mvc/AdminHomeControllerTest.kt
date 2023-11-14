package com.tsurutan.headlessbms.controllers.mvc

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AdminHomeControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Nested
    @DisplayName("Get /admin")
    inner class GetAdmin {
        @Test
        fun shouldReturnOk() {
            webTestClient
                .get()
                .uri("/admin")
                .exchange()
                .expectStatus().isOk
        }
    }
}