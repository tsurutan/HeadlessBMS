package com.tsurutan.headlessbms.controllers.api

import com.tsurutan.headlessbms.entities.ArticleEntity
import com.tsurutan.headlessbms.repositories.ArticleRepository
import jakarta.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.web.reactive.server.WebTestClient


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ArticleControllerWebTestClientTest {
    @LocalServerPort
    var port = 0
    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var entityManager: EntityManager

    @Nested
    @DisplayName("GET: /api/articles")
    inner class GetArticles {
        @Test
        fun shouldReturnOk() {
            request().expectStatus().isOk
        }

        @Test
        fun shouldReturnArticles() {
            articleRepository.save(ArticleEntity(slug = "sample", title = "記事です"))

            request().expectBody().jsonPath("$[0].title", "記事です")
        }

        private fun request(): WebTestClient.ResponseSpec {
            return webTestClient
                .get().uri("/api/articles").exchange()
        }
    }

    @Nested
    @DisplayName("POST: /api/articles")
    inner class PostArticles {

        @Test
        fun shouldReturnIsCreated() {
            request().expectStatus().isCreated
        }

        @Test
        fun shouldReturnLocation() {
            request().expectHeader().location("http://localhost:${port}/api/articles/1")
        }

        @Test
        fun shouldCreateArticle() {
            val builder = entityManager.criteriaBuilder
            val criteriaQuery = builder.createQuery()
            val articleRoot = criteriaQuery.from(ArticleEntity::class.java)
            val countQuery = criteriaQuery.select(builder.count(articleRoot))
            val count = entityManager.createQuery(countQuery).singleResult
            assertThat(count).isEqualTo(0L)

            request().expectHeader().location("http://localhost:${port}/api/articles/1")

            assertThat(entityManager.createQuery(countQuery).singleResult).isEqualTo(1L)
        }

        private fun request(): WebTestClient.ResponseSpec {
            return webTestClient
                .post().uri("/api/articles").exchange()
        }
    }
}
