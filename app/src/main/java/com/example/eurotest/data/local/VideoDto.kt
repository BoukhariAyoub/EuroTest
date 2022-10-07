package com.example.eurotest.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideoDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "thumb") var thumb: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "date") var date: Double,
    @Embedded(prefix = "sport") val sport: SportDto,
    @ColumnInfo(name = "views") var views: Int
)