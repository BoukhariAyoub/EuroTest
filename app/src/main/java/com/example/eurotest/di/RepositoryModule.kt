package com.example.eurotest.di


import com.example.eurotest.data.local.LocalService
import com.example.eurotest.data.remote.RetrofitService
import com.example.eurotest.data.repository.ArticlesRepositoryImpl
import com.example.eurotest.domain.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArticlesRepository(
        remoteService: RetrofitService,
        localService: LocalService,
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(remoteService,localService)
    }
}

