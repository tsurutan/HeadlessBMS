package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import com.tsurutan.headlessbms.services.Article

class NewArticlePage(page: Page): BasePage(page) {
    private val articleFormMixin: ArticleFormMixin

    init {
        articleFormMixin = ArticleFormMixin(page)
    }
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
