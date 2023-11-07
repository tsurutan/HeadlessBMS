package com.tsurutan.headlessbms.e2e

import com.microsoft.playwright.Browser
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.tsurutan.headlessbms.e2e.pages.ArticlesPage
import com.tsurutan.headlessbms.e2e.pages.EditArticlePage
import com.tsurutan.headlessbms.e2e.pages.NewArticlePage
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy

// TODO: Figure out the difference between @Configuration and @TestConfiguration
@Lazy // The Beans in the Lazy Configuration class will be instantiated at last, so the local server port is available
@TestConfiguration
class E2EConfiguration {
    @LocalServerPort
    var port: Int = 0
    @Bean
    fun baseUrl(): String {
       return "http://localhost:$port"
    }
    @Bean
    fun page(baseUrl: String): Page {
        val playwright = Playwright.create()
        val browser = playwright.chromium().launch()
        return browser.newPage(Browser.NewPageOptions().setBaseURL(baseUrl))
    }

    @Bean
    fun newArticlePage(page: Page): NewArticlePage {
        return NewArticlePage(page)
    }

    @Bean
    fun articlesPage(page: Page): ArticlesPage {
        return ArticlesPage(page)
    }

    @Bean
    fun editArticlePage(page: Page): EditArticlePage {
        return EditArticlePage(page)
    }
}
