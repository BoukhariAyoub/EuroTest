package com.example.eurotest.domain.model

data class Video(
    override val id: Int,
    override val title: String,
    override val date: Double,
    val thumb: String,
    val url: String,
    val sport: Sport,
    val views: Int = 0
) : Article(id, title, date)