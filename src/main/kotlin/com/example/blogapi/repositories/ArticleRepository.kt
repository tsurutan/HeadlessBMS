package com.example.blogapi.repositories

import com.example.blogapi.entities.ArticleEntity
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<ArticleEntity, String>