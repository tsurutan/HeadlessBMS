package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import com.tsurutan.headlessbms.services.Article

class NewArticlePage(page: Page): BasePage(page) {
    fun goTo() {
        page.navigate("/admin/articles/new")
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

    fun save() {
        page.getByText("Save").click()
    }
}
