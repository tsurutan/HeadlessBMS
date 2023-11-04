package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import com.tsurutan.headlessbms.services.Article

class ArticlesPage(page: Page): BasePage(page) {
    fun clickLink(article: Article) {
        page.getByText(article.title).click()
    }
}
