package com.example.eurotest.domain

import androidx.annotation.VisibleForTesting
import com.example.eurotest.domain.model.Article
import com.example.eurotest.domain.model.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.zip

class ArticlesUseCase(
    private val articlesRepository: ArticlesRepository
) {

    suspend fun downloadArticles() {
        articlesRepository.downloadArticles()
    }

    fun getSortedArticles(): Flow<List<Article>> {
        return articlesRepository.getVideos()
            .zip(articlesRepository.getStories()) { videos, stories ->
                stories.sortedByDescending { it.date }.mix(videos.sortedByDescending { it.date })
            }
            .distinctUntilChanged()
    }

    suspend fun getStoryById(id: Int): Story {
        return articlesRepository.getStoryById(id)
    }

    /**
     * merge two lists by alternating elements
     */
    @VisibleForTesting
    fun <T> List<T>.mix(other: List<T>): List<T> {
        val first = iterator()
        val second = other.iterator()
        val list = ArrayList<T>(minOf(this.size, other.size))

        while (first.hasNext() || second.hasNext()) {
            if (first.hasNext()) list.add(first.next())
            if (second.hasNext()) list.add(second.next())
        }
        return list
    }
}