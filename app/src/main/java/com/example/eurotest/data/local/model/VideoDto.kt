package com.example.eurotest.data.local.model

import androidx.room.*

@Entity(tableName = "video")
data class VideoDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "thumb") var thumb: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "date") var date: Double,
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix = "sport") val sport: SportDto,
    @ColumnInfo(name = "views") var views: Int
)