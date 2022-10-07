package com.example.eurotest.di

import android.content.Context
import com.example.eurotest.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideVideoDao(appDatabase: AppDatabase): VideoDao {
        return appDatabase.videoDao()
    }

    @Provides
    fun provideStoryDao(appDatabase: AppDatabase): StoryDao {
        return appDatabase.storyDao()
    }

    @Provides
    fun provideLocalService(storyDao: StoryDao, videoDao: VideoDao): LocalService {
        return LocalServiceImpl(videoDao, storyDao)
    }
}