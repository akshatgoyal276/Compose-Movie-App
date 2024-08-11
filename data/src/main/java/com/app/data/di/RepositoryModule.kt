package com.app.data.di

import com.app.data.repository.MovieRepositoryImpl
import com.app.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesRepository(repositoryImpl: MovieRepositoryImpl): MoviesRepository
}