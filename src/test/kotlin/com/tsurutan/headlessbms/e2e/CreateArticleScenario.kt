package com.tsurutan.headlessbms.e2e

import com.tsurutan.headlessbms.e2e.pages.ArticlesPage
import com.tsurutan.headlessbms.e2e.pages.EditArticlePage
import com.tsurutan.headlessbms.e2e.pages.NewArticlePage
import com.tsurutan.headlessbms.services.Article
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [E2EConfiguration::class]) // TODO: What is @ContextConfiguration
class CreateArticleScenario {
    @Autowired
    lateinit var newArticlePage: NewArticlePage
    @Autowired
    lateinit var articlesPage: ArticlesPage
    @Autowired
    lateinit var editArticlePage: EditArticlePage


    @Test
    fun shouldCreateArticle() {
        val newArticle = Article(
            title="This is the title of article",
            slug="hello",
            description = "description Sample",
            content = "This is the content"
        )
        newArticlePage.goTo()

        newArticlePage.expectFormToBeInTheDocument()
        newArticlePage.fillForms(newArticle)
        newArticlePage.save()

        articlesPage.expectPageIsInThis()
        articlesPage.expectArticleToBeInTheDocument(newArticle)
        articlesPage.clickLink()

        editArticlePage.expectPageIsInThis(newArticle)
        editArticlePage.expectTitleToBe("This is the title of article")
        editArticlePage.expectFormWithValuesToBeInTheDocument(newArticle)
    }
}
