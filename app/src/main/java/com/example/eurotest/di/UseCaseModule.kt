package com.example.eurotest.di

import com.example.eurotest.domain.ArticlesRepository
import com.example.eurotest.domain.ArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideArticleUseCase(
        articleRepository: ArticlesRepository
    ): ArticlesUseCase {
        return ArticlesUseCase(
            articleRepository
        )
    }
}

