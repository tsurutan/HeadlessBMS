package com.tsurutan.headlessbms.e2e.pages

import com.microsoft.playwright.Page
import org.assertj.core.api.Assertions

sealed class PagePath(val value: String) {
    object Home: PagePath("/admin")
    object Articles: PagePath("/admin/articles")
    object NewArticle: PagePath("/admin/articles/new")
}

open class BasePage(protected val page: Page) {
    fun expectTitleToBe(title: String) {
        Assertions.assertThat(page.title()).isEqualTo(title)
    }

    fun expectCurrentPage(pagePath: PagePath) {
        Assertions.assertThat(page.url()).endsWith(pagePath.value)
    }
}
