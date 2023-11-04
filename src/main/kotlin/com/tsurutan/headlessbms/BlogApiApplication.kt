package com.tsurutan.headlessbms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogApiApplication

fun main(args: Array<String>) {
    runApplication<BlogApiApplication>(*args)
}
