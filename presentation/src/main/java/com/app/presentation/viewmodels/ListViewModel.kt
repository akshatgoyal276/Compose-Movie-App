package com.app.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.app.domain.model.uiView.MovieView
import com.app.domain.useCases.MovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val movieListUseCase: MovieListUseCase) :
    ViewModel() {

    var searchQuery by mutableStateOf("")
    val movieList: Flow<PagingData<MovieView>>
        get() = snapshotFlow {
                    searchQuery
                }.combine(getMovies()) { searchQuery, movies ->
                    when {
                        searchQuery.isNotEmpty() -> movies.filter { movie ->
                            movie.title.contains(searchQuery, ignoreCase = true)
                        }
                        else -> movies
                    }
                }
    private fun getMovies() = movieListUseCase.invoke().cachedIn(viewModelScope)
}