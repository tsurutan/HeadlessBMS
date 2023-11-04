package com.tsurutan.headlessbms.repositories

import com.tsurutan.headlessbms.entities.ArticleEntity
import org.springframework.data.repository.CrudRepository

interface ArticleRepository: CrudRepository<ArticleEntity, String>
