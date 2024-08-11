package com.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.converters.toMovieDetail
import com.app.domain.model.NetworkResult
import com.app.domain.model.UiState
import com.app.domain.useCases.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieDetailUseCase: MovieDetailUseCase) :
    ViewModel() {
    private val _detailResponse: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(true, null, false))
    val movieDetail get() = _detailResponse

    fun getMovieDetail(id: Int) = viewModelScope.launch {
        _detailResponse.emit(UiState(true, null, false))
        when (val response = movieDetailUseCase.invoke(id)) {
            is NetworkResult.Success -> {
                _detailResponse.emit(UiState(false, response.value.toMovieDetail(), false))
            }
            is NetworkResult.Failure -> {
                _detailResponse.emit(UiState(false, null, true))
            }

        }
    }
}