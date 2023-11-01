package com.example.blogapi.controllers.mvc

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class AdminArticleControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("GET /admin/articles")
    inner class GetAdminArticles {
        @Test
        fun `OKを返す`() {
            request().andExpect(status().isOk)
        }

        @Test
        fun `articlesのviewが表示される`() {
            request().andExpect(view().name("admin/articles"))
        }

        @Test
        fun `modelがviewに設定される`() {
            request().andExpect(model().attributeExists("articles"))
        }

        private fun request(): ResultActions {
            return mockMvc.perform(get("/admin/articles"))
        }
    }
}
