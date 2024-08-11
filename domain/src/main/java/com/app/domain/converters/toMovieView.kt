package com.app.domain.converters

import com.app.commons.IMAGE_URL
import com.app.domain.model.movieList.Movie
import com.app.domain.model.uiView.MovieView

fun Movie.toMovieView() = MovieView(
    title = title,
    id = id,
    release_date = release_date?.substringBefore("-") ?: "",
    vote = vote_average.toString(),
    image = IMAGE_URL + poster_path
)