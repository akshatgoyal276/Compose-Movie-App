package com.app.domain.model.uiView

import com.app.domain.model.movieDetail.Genre

data class MovieDetail(
    val title: String,
    val image: String,
    val vote: String,
    val release_date: String?,
    val overView: String,
    val genres: List<Genre>
)
