package com.example.eurotest.domain.model

data class Story(
    override val id: Int,
    override val title: String,
    override val date: Double,
    val teaser: String,
    val image: String,
    val author: String,
    val sport: Sport,
) : Article(id, title, date)