package com.tsurutan.headlessbms.controllers.api

import com.tsurutan.headlessbms.services.Article
import com.tsurutan.headlessbms.services.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

data class ArticleResponse(val title: String) {
    companion object {
        fun create(article: Article): ArticleResponse {
            return ArticleResponse(title=article.title)
        }
    }
}

@RestController
@RequestMapping("/api/articles")
class ArticleController(val articleService: ArticleService) {
    @GetMapping
    fun getArticles(): List<ArticleResponse> {
        return articleService.getArticles().map(ArticleResponse.Companion::create)
    }

    @PostMapping
    fun createArticle(): ResponseEntity<Unit> {
        articleService.save("")
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(1).toUri()
        return ResponseEntity.created(uri).build()
    }
}
