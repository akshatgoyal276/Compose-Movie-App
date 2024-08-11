package com.app.domain.di

import com.app.domain.repository.MoviesRepository
import com.app.domain.useCases.MovieDetailUseCase
import com.app.domain.useCases.MovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideListUseCase(moviesRepository: MoviesRepository) =
        MovieListUseCase(moviesRepository)

    @Provides
    @Singleton
    fun provideDetailUseCase(moviesRepository: MoviesRepository) =
        MovieDetailUseCase(moviesRepository)
}