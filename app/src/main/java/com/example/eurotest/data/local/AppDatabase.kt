package com.example.eurotest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eurotest.data.local.model.SportDto
import com.example.eurotest.data.local.model.StoryDto
import com.example.eurotest.data.local.model.VideoDto

@Database(
    entities = [StoryDto::class, VideoDto::class, SportDto::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun storyDao(): StoryDao

    companion object {
        private const val DATABASE_NAME = "eurosport-database"
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}