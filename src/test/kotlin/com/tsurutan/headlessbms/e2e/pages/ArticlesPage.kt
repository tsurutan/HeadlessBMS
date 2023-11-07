package com.tsurutan.headlessbms.e2e.pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions.assertThat

class ArticlesPage(page: Page): BasePage(page) {
    fun expectPageIsInThis() {
        assertThat(page.url()).endsWith("/admin/articles")
    }
    fun clickLink() {
        page.getByRole(AriaRole.LINK, Page.GetByRoleOptions().setName("Edit")).click()
    }

    fun expectArticleToBeInTheDocument(article: Article) {
        assertThat(page.getByText(article.title).isVisible).isTrue()
        assertThat(page.getByText(article.description).isVisible).isTrue()
    }
}
