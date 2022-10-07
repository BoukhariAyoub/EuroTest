package com.example.eurotest.domain

import com.example.eurotest.domain.model.Article
import com.example.eurotest.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun downloadArticles()
    fun getVideos(): Flow<List<Article>>
    fun getStories(): Flow<List<Article>>
    suspend fun getStoryById(id: Int): Story
}
