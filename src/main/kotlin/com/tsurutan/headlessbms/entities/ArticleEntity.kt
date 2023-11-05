package com.tsurutan.headlessbms.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class ArticleEntity(
    @Id val slug: String,
    val title: String,
    val description: String
)
