package com.app.domain.converters

import com.app.commons.IMAGE_URL
import com.app.domain.model.movieDetail.MovieDetailResponse
import com.app.domain.model.uiView.MovieDetail

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    title = title,
    release_date = release_date?.substringBefore("-") ?: "",
    vote = vote_average.toString(),
    image = IMAGE_URL + poster_path,
    overView = overview,
    genres = genres
)