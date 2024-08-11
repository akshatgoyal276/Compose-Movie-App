package com.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.domain.model.uiView.MovieView
import com.app.domain.useCases.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val movieListUseCase: MovieListUseCase) :
    ViewModel() {

    val movieList: Flow<PagingData<MovieView>> get() = getMovies()
    private fun getMovies() = movieListUseCase.invoke().cachedIn(viewModelScope)
}