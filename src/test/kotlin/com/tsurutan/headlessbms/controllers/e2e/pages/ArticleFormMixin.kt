package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions

class ArticleFormMixin(private val page: Page) {
    fun expectFormWithValuesToBeInTheDocument(article: Article) {
        page.getByLabel("Slug").isVisible
        Assertions.assertThat(page.getByPlaceholder("Please input slug").inputValue()).isEqualTo(article.slug)
        page.getByLabel("Title").isVisible
        Assertions.assertThat(page.getByPlaceholder("Please input title").inputValue()).isEqualTo(article.title)
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Update")).isVisible
    }

    fun expectFormToBeInTheDocument() {
        page.getByLabel("Slug").isVisible
        page.getByPlaceholder("Please input slug").fill("hello")
        page.getByLabel("Title").isVisible
        page.getByPlaceholder("Please input title").fill("This is the title of article")
    }

    fun fillForms(article: Article) {
        page.getByPlaceholder("Please input slug").fill(article.slug)
        page.getByPlaceholder("Please input title").fill(article.title)
    }
}
