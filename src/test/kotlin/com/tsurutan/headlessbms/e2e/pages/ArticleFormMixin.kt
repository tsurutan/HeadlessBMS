package com.tsurutan.headlessbms.e2e.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions

class ArticleFormMixin(private val page: Page) {
    private val slugInput: Locator
        get() = page.getByPlaceholder("Please input slug")
    private val titleInput: Locator
        get() = page.getByPlaceholder("Please input title")
    private val descriptionTextArea: Locator
        get() = page.getByPlaceholder("Please input description")

    private val contentTextArea: Locator
        get() = page.getByPlaceholder("Please input content")

    fun expectFormWithValuesToBeInTheDocument(article: Article) {
        Assertions.assertThat(slugInput.inputValue()).isEqualTo(article.slug)
        Assertions.assertThat(titleInput.inputValue()).isEqualTo(article.title)
        Assertions.assertThat(descriptionTextArea.inputValue()).isEqualTo(article.description)
        Assertions.assertThat(contentTextArea.inputValue()).isEqualTo(article.content)
    }

    fun expectFormToBeInTheDocument() {
        page.getByLabel("Slug").isVisible
        slugInput.isVisible
        page.getByLabel("Title").isVisible
        titleInput.isVisible
        page.getByLabel("Description").isVisible
        descriptionTextArea.isVisible
        page.getByLabel("Content").isVisible
        contentTextArea.isVisible
    }

    fun fillForms(article: Article) {
        slugInput.fill(article.slug)
        titleInput.fill(article.title)
        descriptionTextArea.fill(article.description)
        contentTextArea.fill(article.content)
    }
}
