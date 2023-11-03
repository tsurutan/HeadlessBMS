package com.example.blogapi.controllers.mvc

import com.example.blogapi.services.ArticleService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

data class NewArticle(val slug: String = "")

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController(val articleService: ArticleService) {
    @GetMapping("/new")
    fun createArticlePage(model: Model): String {
        return "admin/articles/new"
    }

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    // ASK: Why does this work? new.html doesn't specify newArticle, though.
    fun createArticle(newArticle: NewArticle): String {
        articleService.save(slug=newArticle.slug)
        return "redirect:/admin/articles"
    }

    @GetMapping
    fun getArticles(model: Model): String {
        val articles = articleService.getArticles()
        model.addAttribute("articles", articles)
        model.addAttribute("newArticle", NewArticle())
        return "admin/articles"
    }
}
