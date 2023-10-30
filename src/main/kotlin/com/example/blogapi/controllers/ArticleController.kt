package com.example.blogapi.controllers

import com.example.blogapi.services.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class ArticleResponse(val title: String)

@RestController
@RequestMapping("/api/articles")
class ArticleController(val articleService: ArticleService) {
    @GetMapping
    fun getArticles(): List<ArticleResponse> {
        return articleService.getArticles().map { ArticleResponse(title=it.title) }
    }
}