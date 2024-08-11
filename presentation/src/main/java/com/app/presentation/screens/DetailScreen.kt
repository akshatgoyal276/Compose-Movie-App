package com.app.presentation.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.presentation.components.ErrorView
import com.app.presentation.components.Loader
import com.app.presentation.components.MovieDetails
import com.app.presentation.viewmodels.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detail = remember {
        viewModel.getMovieDetail(movieId.toInt())
        viewModel.movieDetail
    }.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            when {
                detail.value.isLoading -> {
                    Loader()
                }
                detail.value.data != null -> {
                    MovieDetails(detail.value)
                }
                detail.value.error -> {
                    ErrorView { viewModel.getMovieDetail(movieId.toInt()) }
                }
            }
        })
}