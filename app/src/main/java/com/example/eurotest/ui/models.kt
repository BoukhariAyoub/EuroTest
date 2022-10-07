package com.example.eurotest.ui

import com.example.eurotest.domain.model.Article
import com.example.eurotest.domain.model.Story
import com.example.eurotest.domain.model.Video
import getTimeAgo

sealed class ArticleUi(
    open val id: Int,
    open val title: String,
)

data class StoryUi(
    override val id: Int,
    override val title: String,
    val date: String,
    val teaser: String,
    val image: String,
    val author: String,
    val sport: String,
) : ArticleUi(id, title)


data class VideoUi(
    override val id: Int,
    override val title: String,
    val thumb: String,
    val url: String,
    val sport: String,
    val views: Int = 0
) : ArticleUi(id, title)


fun Article.toUi() = when (this) {
    is Story -> this.toUi()
    is Video -> this.toUi()
}

fun Story.toUi() = StoryUi(
    id = id,
    title = title,
    date = getTimeAgo(date.toLong()) ?: "error date",
    teaser = teaser,
    image = image,
    author = author,
    sport = sport.name
)

fun Video.toUi() = VideoUi(
    id = id,
    title = title,
    thumb = thumb,
    url = url,
    sport = sport.name
)