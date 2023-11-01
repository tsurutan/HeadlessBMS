package com.example.blogapi.controllers.mvc

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController {
    @GetMapping
    fun getArticles(model: Model): String {
        model.addAttribute("articles", emptyList<String>())
        return "admin/articles"
    }
}
