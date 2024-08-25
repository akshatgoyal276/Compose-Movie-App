package com.app.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            CustomSearchView(search = viewModel.searchQuery ) {
                viewModel.searchQuery = it
            }
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

@Composable
fun CustomSearchView(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(CircleShape)
            .fillMaxWidth()
            .background(Color(0xFFFFFFFF))

    ) {
        TextField(value = search,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0x1A808080),
                placeholderColor = Color(0xFF23009C),
                leadingIconColor = Color(0xFF2901B6),
                textColor = Color(0xFF23009C),
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color(0x80000000)
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = { Text(text = "Search Movies") }
        )
    }

}