package com.app.domain.repository

import androidx.paging.PagingData
import com.app.domain.model.NetworkResult
import com.app.domain.model.movieDetail.MovieDetailResponse
import com.app.domain.model.uiView.MovieView
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(): Flow<PagingData<MovieView>>
    suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailResponse>
}