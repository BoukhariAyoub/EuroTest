package com.example.eurotest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eurotest.data.local.model.StoryDto
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {

    @Query("SELECT * FROM story")
    fun getAll(): Flow<List<StoryDto>>

    @Query("SELECT * FROM story WHERE id = :id")
    suspend fun getById(id: Int): StoryDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stories: List<StoryDto>)
}