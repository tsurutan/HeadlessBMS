package com.tsurutan.headlessbms.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name="articles")
data class ArticleEntity(
    @Id val slug: String,
    val title: String,
    val description: String,
    val content: String
)
