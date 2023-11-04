package com.tsurutan.headlessbms.controllers.e2e.pages

import com.microsoft.playwright.Page
import org.assertj.core.api.Assertions

open class BasePage(protected val page: Page) {
    fun expectTitleToBe(title: String) {
        Assertions.assertThat(page.title()).isEqualTo("This is the title of article")
    }
}
