package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions.assertThat

class EditArticlePage(page: Page): BasePage(page) {
    fun expectFormWithValuesToBeInTheDocument(article: Article) {
        page.getByLabel("Slug").isVisible
        assertThat(page.getByPlaceholder("Please input slug").inputValue()).isEqualTo(article.slug)
        page.getByLabel("Title").isVisible
        assertThat(page.getByPlaceholder("Please input title").inputValue()).isEqualTo(article.title)
        page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Update")).isVisible
    }
}
