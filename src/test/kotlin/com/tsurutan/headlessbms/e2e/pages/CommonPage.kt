package com.tsurutan.headlessbms.e2e.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.AriaRole
import com.tsurutan.headlessbms.e2e.annotations.PageObject

@PageObject
class CommonPage(page: Page): BasePage(page) {
    private val header: Locator
        get() = page.getByRole(AriaRole.NAVIGATION)
    fun goToHome() {
        page.navigate(PagePath.Home.value)
    }
    fun clickHomeLink() {
        header
            .getByRole(AriaRole.LINK, Locator.GetByRoleOptions().setName("Home"))
            .click()
    }
    fun clickArticlesLink() {
        header
            .getByRole(AriaRole.LINK, Locator.GetByRoleOptions().setName("Articles"))
            .click()
    }

    fun clickNewLink() {
        header
            .getByRole(AriaRole.LINK, Locator.GetByRoleOptions().setName("New"))
            .click()
    }
}