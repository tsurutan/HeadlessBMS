package com.example.blogapi.services

import com.example.blogapi.entities.ArticleEntity
import com.example.blogapi.repositories.ArticleRepository
import org.springframework.stereotype.Service

data class Article(val title: String)

@Service
class ArticleService(val articleRepository: ArticleRepository) {
    fun getArticles(): List<Article> {
        return articleRepository.findAll().map { Article(title=it.title) }
    }

    fun save() {
        articleRepository.save(ArticleEntity(slug="sample", title="Article"))
    }
}
