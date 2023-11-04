package com.tsurutan.headlessbms.controllers.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateArticleScenario {
    @LocalServerPort
    var port: Int = 0
    lateinit var page: Page

    @BeforeEach
    fun setup() {
        val playwright = Playwright.create()
        val browser = playwright.chromium().launch()
        page = browser.newPage(Browser.NewPageOptions().setBaseURL("http://localhost:${port}"))
    }

    @Test
    fun shouldCreateArticle() {
        page.navigate("/admin/articles/new")
        page.getByLabel("Slug").isVisible
        page.getByPlaceholder("Please input slug").fill("hello")
        page.getByLabel("Title").isVisible
        page.getByPlaceholder("Please input title").fill("This is the title of article")
        page.getByText("Save").click()

        assertThat(
            page.getByText("This is the title of article")
                .getAttribute("href")
        ).isEqualTo("/admin/articles/hello")
    }
}
