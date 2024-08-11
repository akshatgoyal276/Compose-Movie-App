package com.app.data.api

import com.app.domain.model.movieDetail.MovieDetailResponse
import com.app.domain.model.movieList.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("trending/movie/week")
    suspend fun getMovies(
        @Query("page") page: Int? = null,
    ): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int
    ): MovieDetailResponse
}