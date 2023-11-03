package com.example.blogapi.controllers.mvc

import com.example.blogapi.services.ArticleService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController(val articleService: ArticleService) {
    @GetMapping("/new")
    fun createArticlePage(model: Model): String {
        return "admin/articles/new"
    }

    @PostMapping
    fun createArticle(model: Model): String {
        articleService.save()
        return "redirect:/admin/articles"
    }

    @GetMapping
    fun getArticles(model: Model): String {
        val articles = articleService.getArticles()
        model.addAttribute("articles", articles)
        return "admin/articles"
    }
}
