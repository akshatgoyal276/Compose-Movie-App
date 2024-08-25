package com.app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.domain.model.uiView.MovieView
import com.app.presentation.R
import com.app.presentation.utils.Screen

@Composable
fun MovieListItem(navController: NavController, movie: MovieView) {
    Box(
        Modifier
            .clickable { navController.navigate(Screen.Detail.createRoute(movie.id.toString())) }
            .fillMaxWidth()) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.image)
                    .crossfade(true)
                    .placeholder(R.drawable.movie_place_holder)
                    .error(R.drawable.movie_place_holder)
                    .build(),
                contentDescription = "movie Image",
                contentScale = ContentScale.Crop
            )
            Text(
                text = movie.title,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .height(0.7.dp)
        )
    }
}