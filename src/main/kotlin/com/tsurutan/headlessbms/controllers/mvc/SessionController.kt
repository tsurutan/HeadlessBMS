package com.tsurutan.headlessbms.controllers.mvc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SessionController {
    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}
