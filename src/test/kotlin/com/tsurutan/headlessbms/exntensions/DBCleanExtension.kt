package com.tsurutan.headlessbms.exntensions

import com.tsurutan.headlessbms.repositories.ArticleRepository
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.stereotype.Component
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@Component
class DBCleanExtension: AfterEachCallback {
    @Transactional
    override fun afterEach(p0: ExtensionContext) {
        val context = SpringExtension.getApplicationContext(p0)
        val repository = context.getBean(ArticleRepository::class.java)
        repository.deleteAll()
    }
}