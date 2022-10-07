package com.example.eurotest.data.local

import kotlinx.coroutines.flow.Flow

interface LocalService {

    fun insertVideos(videos: List<VideoDto>)
    fun insertStories(stories: List<StoryDto>)
    fun getVideos(): Flow<List<VideoDto>>
    fun getStories(): Flow<List<StoryDto>>
    suspend fun getStoryById(id: Int): StoryDto
}

class LocalServiceImpl(
    private val videoDao: VideoDao,
    private val storyDao: StoryDao
) : LocalService {

    override fun insertVideos(videos: List<VideoDto>) {
        videoDao.insertAll(videos)
    }

    override fun insertStories(stories: List<StoryDto>) {
        storyDao.insertAll(stories)
    }

    override fun getVideos(): Flow<List<VideoDto>> {
        return videoDao.getAll()
    }

    override fun getStories(): Flow<List<StoryDto>> {
        return storyDao.getAll()
    }

    override suspend fun getStoryById(id: Int): StoryDto {
        return storyDao.getById(id)
    }
}