package com.app.domain.model

import com.app.domain.model.uiView.MovieDetail

data class UiState(
    val isLoading: Boolean = false,
    val data: MovieDetail? = null,
    val error: Boolean = false
)