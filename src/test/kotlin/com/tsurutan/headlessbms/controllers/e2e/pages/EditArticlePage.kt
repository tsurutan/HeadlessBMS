package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions.assertThat

class EditArticlePage(page: Page): BasePage(page) {
    private val articleFormMixin: ArticleFormMixin
    init {
        articleFormMixin = ArticleFormMixin(page)
    }

    fun expectFormWithValuesToBeInTheDocument(article: Article) {
        articleFormMixin.expectFormWithValuesToBeInTheDocument(article)
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Update")).isVisible
    }
}
