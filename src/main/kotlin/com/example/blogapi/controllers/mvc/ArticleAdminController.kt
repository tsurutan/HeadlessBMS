package com.example.blogapi.controllers.mvc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/articles")
class ArticleAdminController {
    @GetMapping
    fun getArticles(): String {
        return "admin/articles"
    }
}