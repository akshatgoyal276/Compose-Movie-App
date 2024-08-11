package com.app.domain.useCases

import androidx.paging.PagingData
import com.app.domain.model.uiView.MovieView
import com.app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieListUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    fun invoke(): Flow<PagingData<MovieView>> {
        return moviesRepository.getMovies()
    }
}