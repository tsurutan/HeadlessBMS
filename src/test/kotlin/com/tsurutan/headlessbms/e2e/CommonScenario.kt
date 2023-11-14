package com.tsurutan.headlessbms.e2e

import com.tsurutan.headlessbms.e2e.annotations.E2E
import com.tsurutan.headlessbms.e2e.pages.CommonPage
import com.tsurutan.headlessbms.e2e.pages.PagePath
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@E2E
class CommonScenario {
    @Autowired
    lateinit var commonPage: CommonPage

    @Test
    fun shouldNavigateSomePagesFromHeaderLinks() {
        commonPage.goToHome()
        commonPage.clickArticlesLink()
        commonPage.expectCurrentPage(PagePath.Articles)
        commonPage.clickNewLink()
        commonPage.expectCurrentPage(PagePath.NewArticle)
        commonPage.clickHomeLink()
        commonPage.expectCurrentPage(PagePath.Home)
    }
}