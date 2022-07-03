package com.suatzengin.movieapp.screens.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.suatzengin.movieapp.model.Movie
import com.suatzengin.movieapp.model.getMovies
import com.suatzengin.movieapp.navigation.MovieScreens
import com.suatzengin.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") }
            )
        }
    ) {
        MainContent(navController = navController)
    }
}


@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) { item ->
                MovieRow(movie = item) { movie ->
                    navController.navigate(MovieScreens.DetailScreen.name + "/$movie")
                }
            }
        }
    }
}