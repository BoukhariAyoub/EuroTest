package com.example.eurotest.domain.model

sealed class Article(
    open val id: Int,
    open val title: String,
    open val date: Double
)

