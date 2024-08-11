package com.app.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyGridState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.presentation.components.ErrorView
import com.app.presentation.components.LoadingShimmerEffect
import com.app.presentation.components.MovieListItem
import com.app.presentation.viewmodels.ListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(navController: NavController, viewModel: ListViewModel = hiltViewModel()) {
    val lazyMovieItems = remember {
        viewModel.movieList
    }.collectAsLazyPagingItems()

    val listState = rememberLazyGridState()
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Movie App") }
            )
        }
    ) {
        if (lazyMovieItems.loadState.refresh is LoadState.Error) {
            ErrorView { lazyMovieItems.retry() }
        }
        LazyVerticalGrid(cells = GridCells.Fixed(2), state = listState, ) {
            if (lazyMovieItems.loadState.refresh is LoadState.Loading) {
                items(20) {
                    LoadingShimmerEffect()
                }
            }
            items(lazyMovieItems.itemCount) { index ->
                lazyMovieItems[index]?.let { movie ->
                    MovieListItem(navController = navController, movie)
                }
            }
        }
    }
}