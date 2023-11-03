package com.example.blogapi.services

import com.example.blogapi.entities.ArticleEntity
import com.example.blogapi.repositories.ArticleRepository
import org.springframework.stereotype.Service

data class Article(val title: String, val slug: String) {
    companion object {
        fun create(articleEntity: ArticleEntity): Article {
            return Article(title = articleEntity.title, slug = articleEntity.slug)
        }
    }
}

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getArticles(): List<Article> {
        return articleRepository.findAll().map(Article::create)
    }

    fun save(slug: String) {
        articleRepository.save(ArticleEntity(slug = slug, title = "Sample Article"))
    }
}
