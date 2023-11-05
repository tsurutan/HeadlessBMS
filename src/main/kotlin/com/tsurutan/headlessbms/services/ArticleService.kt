package com.tsurutan.headlessbms.services

import com.tsurutan.headlessbms.entities.ArticleEntity
import com.tsurutan.headlessbms.repositories.ArticleRepository
import org.springframework.stereotype.Service

data class Article(val title: String, val slug: String, val description: String) {
    companion object {
        fun create(articleEntity: ArticleEntity): Article {
            return Article(title = articleEntity.title, slug = articleEntity.slug, description = articleEntity.description)
        }
    }
}

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getArticle(slug: String): Article {
        return Article.create(articleRepository.findById(slug).orElseThrow())
    }
    fun getArticles(): List<Article> {
        return articleRepository.findAll().map(Article.Companion::create)
    }

    fun save(slug: String, title: String, description: String) {
        articleRepository.save(ArticleEntity(slug = slug, title = title, description = description))
    }
}
