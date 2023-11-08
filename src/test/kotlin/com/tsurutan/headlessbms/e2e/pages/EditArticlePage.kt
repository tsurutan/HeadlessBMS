package com.tsurutan.headlessbms.e2e.pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.services.Article
import org.assertj.core.api.Assertions.assertThat
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component

@Lazy
@Component
class EditArticlePage(page: Page, val articleFormMixin: ArticleFormMixin): BasePage(page) {
    fun expectPageIsInThis(article: Article) {
        assertThat(page.url()).endsWith("/admin/articles/${article.slug}")
    }

    fun expectFormWithValuesToBeInTheDocument(article: Article) {
        articleFormMixin.expectFormWithValuesToBeInTheDocument(article)
        assertThat(page.getByRole(AriaRole.BUTTON, Page.GetByRoleOptions().setName("Update")).isVisible).isTrue()
    }
}
