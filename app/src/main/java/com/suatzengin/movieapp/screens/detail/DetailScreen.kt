package com.suatzengin.movieapp.screens.detail


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.suatzengin.movieapp.model.Movie
import com.suatzengin.movieapp.model.getMovies
import com.suatzengin.movieapp.widgets.MovieRow


@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().filter { movie ->
        movie.id == movieId
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = movie.first().title) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(
                    state = rememberScrollState()
                )
            ) {
                MovieRow(movie = movie.first(), isExpanded = true)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()

                Text(text = "Movie Images")

                MovieImagesHorizontal(movie)
            }
        }
    }
}

@Composable
fun MovieImagesHorizontal(movie: List<Movie>) {
    LazyRow {

        items(movie.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 6.dp
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    loading = {
                        CircularProgressIndicator(color = Color.DarkGray)
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}