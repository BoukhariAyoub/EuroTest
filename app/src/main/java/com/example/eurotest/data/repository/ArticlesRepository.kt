package com.example.eurotest.data.repository

import com.example.eurotest.data.local.model.VideoDto
import com.example.eurotest.data.local.LocalService
import com.example.eurotest.data.local.model.SportDto
import com.example.eurotest.data.local.model.StoryDto
import com.example.eurotest.data.remote.model.SportResponse
import com.example.eurotest.data.remote.model.StoriesResponse
import com.example.eurotest.data.remote.model.VideosResponse
import com.example.eurotest.data.remote.RetrofitService
import com.example.eurotest.domain.ArticlesRepository
import com.example.eurotest.domain.model.Article
import com.example.eurotest.domain.model.Sport
import com.example.eurotest.domain.model.Story
import com.example.eurotest.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesRepositoryImpl(
    private val remoteService: RetrofitService,
    private val localService: LocalService
) : ArticlesRepository {

    override fun getStories(): Flow<List<Article>> {
        return localService.getStories()
            .map {
                it.map {
                    it.toDomain()
                }
            }

    }

    override fun getVideos(): Flow<List<Article>> {
        return localService.getVideos()
            .map {
                it.map {
                    it.toDomain()
                }
            }
    }

    override suspend fun downloadArticles() {
        val response = remoteService.get()
        val stories = response.stories.map { it.toDomain() }
        val videos = response.videos.map { it.toDomain() }
        localService.insertStories(stories.map { it.toDto() })
        localService.insertVideos(videos.map { it.toDto() })
    }

    override suspend fun getStoryById(id: Int): Story {
        return localService.getStoryById(id).toDomain() as Story
    }

    private fun StoryDto.toDomain(): Article {
        return Story(
            id = this.id,
            title = this.title,
            date = this.date,
            sport = this.sport.toDomain(),
            teaser = this.teaser,
            image = image,
            author = author,
        )
    }

    private fun VideoDto.toDomain(): Article {
        return Video(
            id = this.id,
            title = this.title,
            date = this.date,
            thumb = this.thumb,
            url = this.url,
            sport = this.sport.toDomain(),
            views = this.views
        )
    }

    private fun SportDto.toDomain(): Sport {
        return Sport(
            id = this.id,
            name = this.name
        )
    }

    private fun VideosResponse.toDomain(): Video {
        return Video(
            id = this.id ?: error("Incomplete Item Info"),
            title = this.title ?: error("Incomplete Item Info"),
            date = this.date ?: error("Incomplete Item Info"),
            thumb = this.thumb ?: error("Incomplete Item Info"),
            url = this.url ?: error("Incomplete Item Info"),
            sport = this.sport?.toDomain() ?: error("Incomplete Item Info"),
            views = this.views ?: error("Incomplete Item Info")
        )
    }

    private fun StoriesResponse.toDomain(): Story {
        return Story(
            id = this.id ?: error("Incomplete Item Info"),
            title = this.title ?: error("Incomplete Item Info"),
            date = this.date ?: error("Incomplete Item Info"),
            sport = this.sport?.toDomain() ?: error("Incomplete Item Info"),
            teaser = this.teaser ?: error("Incomplete Item Info"),
            image = image ?: error("Incomplete Item Info"),
            author = author ?: error("Incomplete Item Info"),
        )
    }

    private fun SportResponse.toDomain(): Sport {
        return Sport(
            id = this.id ?: error("Incomplete Item Info"),
            name = this.name ?: error("Incomplete Item Info")
        )
    }

    private fun Video.toDto(): VideoDto {
        return VideoDto(
            id = this.id,
            title = this.title,
            date = this.date,
            thumb = this.thumb,
            url = this.url,
            sport = this.sport.toDto(),
            views = this.views
        )
    }

    private fun Story.toDto(): StoryDto {
        return StoryDto(
            id = this.id,
            title = this.title,
            date = this.date,
            sport = this.sport.toDto(),
            teaser = this.teaser,
            image = image,
            author = author,
        )
    }

    private fun Sport.toDto(): SportDto {
        return SportDto(
            id = id,
            name = name
        )
    }
}

