package com.tsurutan.headlessbms.controllers.e2e

import com.microsoft.playwright.Page
import com.tsurutan.headlessbms.controllers.e2e.pages.ArticlesPage
import com.tsurutan.headlessbms.controllers.e2e.pages.EditArticlePage
import com.tsurutan.headlessbms.controllers.e2e.pages.NewArticlePage
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [E2EConfiguration::class]) // TODO: What is @ContextConfiguration
class CreateArticleScenario {
    @Autowired
    lateinit var page: Page
    @Autowired
    lateinit var newArticlePage: NewArticlePage
    @Autowired
    lateinit var articlesPage: ArticlesPage
    @Autowired
    lateinit var editArticlePage: EditArticlePage
    @Autowired
    lateinit var baseUrl: String


    @Test
    fun shouldCreateArticle() {
        val newArticle = Article(slug="hello", title="This is the title of article")
        newArticlePage.goTo()

        newArticlePage.expectFormToBeInTheDocument()
        newArticlePage.fillForms(newArticle)
        newArticlePage.save()

        articlesPage.clickLink(newArticle)

        assertThat(page.url()).isEqualTo("$baseUrl/admin/articles/hello")

        editArticlePage.expectTitleToBe("This is the title of article")
        editArticlePage.expectFormWithValuesToBeInTheDocument(newArticle)
    }
}
