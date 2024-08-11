package com.app.domain.useCases

import com.app.domain.model.NetworkResult
import com.app.domain.model.movieDetail.MovieDetailResponse
import com.app.domain.repository.MoviesRepository
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(id: Int): NetworkResult<MovieDetailResponse> {
        return moviesRepository.getMovieDetails(id)
    }
}