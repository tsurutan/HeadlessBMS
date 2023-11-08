package com.tsurutan.headlessbms.e2e.pages

import com.microsoft.playwright.Page
import com.tsurutan.headlessbms.services.Article
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
class NewArticlePage(page: Page, val articleFormMixin: ArticleFormMixin): BasePage(page) {
    fun goTo() {
        page.navigate("/admin/articles/new")
    }

    fun expectFormToBeInTheDocument() {
        articleFormMixin.expectFormToBeInTheDocument()
    }

    fun fillForms(article: Article) {
        articleFormMixin.fillForms(article)
    }

    fun save() {
        page.getByText("Save").click()
    }
}
