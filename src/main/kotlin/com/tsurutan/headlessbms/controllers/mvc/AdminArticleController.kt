package com.tsurutan.headlessbms.controllers.mvc

import com.tsurutan.headlessbms.services.ArticleService
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

data class NewArticle(val slug: String = "", val title: String = "", val description: String = "", val content: String)

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController(val articleService: ArticleService) {
    @GetMapping("/{slug}")
    fun editArticlePage(@PathVariable slug: String, model: Model): String {
        val article = articleService.getArticle(slug)
        model.addAttribute("article", article)
        return "admin/articles/edit"
    }

    @GetMapping("/new")
    fun createArticlePage(model: Model): String {
        return "admin/articles/new"
    }

    @PostMapping(consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    // ASK: Why does this work? new.html doesn't specify newArticle, though.
    fun createArticle(newArticle: NewArticle): String {
        articleService.save(
            slug=newArticle.slug,
            title=newArticle.title,
            description = newArticle.description,
            content = newArticle.content
        )
        return "redirect:/admin/articles"
    }

    @GetMapping
    fun getArticles(model: Model): String {
        val articles = articleService.getArticles()
        model.addAttribute("articles", articles)
        model.addAttribute("newArticle", NewArticle(content = ""))
        return "admin/articles"
    }
}
