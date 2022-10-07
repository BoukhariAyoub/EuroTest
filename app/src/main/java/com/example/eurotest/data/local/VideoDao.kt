package com.example.eurotest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Query("SELECT * FROM video")
     fun getAll(): Flow<List<VideoDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<VideoDto>)
}