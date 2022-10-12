package com.example.eurotest.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sport")
data class SportDto(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)