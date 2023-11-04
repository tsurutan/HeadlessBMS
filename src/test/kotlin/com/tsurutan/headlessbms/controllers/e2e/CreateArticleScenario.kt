package com.tsurutan.headlessbms.controllers.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.options.AriaRole
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
    lateinit var baseUrl: String

    @BeforeEach
    fun setup() {
        val playwright = Playwright.create()
        val browser = playwright.chromium().launch()
        baseUrl = "http://localhost:$port"
        page = browser.newPage(Browser.NewPageOptions().setBaseURL(baseUrl))
    }

    @Test
    fun shouldCreateArticle() {
        page.navigate("/admin/articles/new")
        page.getByLabel("Slug").isVisible
        page.getByPlaceholder("Please input slug").fill("hello")
        page.getByLabel("Title").isVisible
        page.getByPlaceholder("Please input title").fill("This is the title of article")
        page.getByText("Save").click()

        page.getByText("This is the title of article").click()

        assertThat(page.url()).isEqualTo("$baseUrl/admin/articles/hello")

        page.getByText("hello").isVisible
        page.getByText("This is the title of article").isVisible
        assertThat(page.title()).isEqualTo("This is the title of article")

        page.getByLabel("Slug").isVisible
        assertThat(page.getByPlaceholder("Please input slug").inputValue()).isEqualTo("hello")
        page.getByLabel("Title").isVisible
        assertThat(page.getByPlaceholder("Please input title").inputValue()).isEqualTo("This is the title of article")
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Update")).isVisible
    }
}
