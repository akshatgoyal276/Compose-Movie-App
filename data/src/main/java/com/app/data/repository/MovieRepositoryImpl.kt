package com.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.data.api.MovieApi
import com.app.data.datasource.MovieDataSource
import com.app.domain.model.NetworkResult
import com.app.domain.model.movieDetail.MovieDetailResponse
import com.app.domain.model.uiView.MovieView
import com.app.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MoviesRepository,
    BaseRepository() {
    override fun getMovies(): Flow<PagingData<MovieView>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 25),
            pagingSourceFactory = {
                MovieDataSource(api)
            }
        ).flow
    }

    override suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailResponse> {
        return safeApiCall { api.getMovieDetail(id) }
    }
}