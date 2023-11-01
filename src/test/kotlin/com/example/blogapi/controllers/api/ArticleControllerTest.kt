package com.example.blogapi.controllers.api

import com.example.blogapi.entities.ArticleEntity
import com.example.blogapi.repositories.ArticleRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


// TODO: WebMvcTestとMockMvcTestの違い
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    lateinit var articleRepository: ArticleRepository

    // MockMVCを使用したテスト
    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("GET: /api/articles")
    inner class MockMVCGetArticles {
        @Test
        fun OKを返す() {
            request().andExpect(status().isOk)
        }

        @Test
        fun 記事の一覧を返す() {
            articleRepository.save(ArticleEntity(slug = "sample", title = "記事です"))

            request()
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("記事です"))
        }

        private fun request(): ResultActions {
            return mockMvc.perform(get("/api/articles"))
        }
    }
}

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ArticleControllerWebTestClientTest {
    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Nested
    @DisplayName("GET: /api/articles")
    inner class MockMVCGetArticles {
        @Test
        fun OKを返す() {
            request().expectStatus().isOk
        }

        @Test
        fun 記事の一覧を返す() {
            articleRepository.save(ArticleEntity(slug = "sample", title = "記事です"))

            request().expectBody().jsonPath("$[0].title", "記事です")
        }

        private fun request(): WebTestClient.ResponseSpec {
            return webTestClient
                .get().uri("/api/articles").exchange()
        }
    }
}