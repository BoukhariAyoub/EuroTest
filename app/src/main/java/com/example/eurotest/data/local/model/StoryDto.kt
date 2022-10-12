package com.example.eurotest.data.local.model

import androidx.room.*

@Entity(tableName = "story")
data class StoryDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "teaser") var teaser: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "date") var date: Double,
    @ColumnInfo(name = "author") var author: String,
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix = "sport") val sport: SportDto,
)