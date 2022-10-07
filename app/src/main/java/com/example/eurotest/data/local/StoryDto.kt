package com.example.eurotest.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story")
data class StoryDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "teaser") var teaser: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "date") var date: Double,
    @ColumnInfo(name = "author") var author: String,
    @Embedded(prefix = "sport") val sport: SportDto,
)