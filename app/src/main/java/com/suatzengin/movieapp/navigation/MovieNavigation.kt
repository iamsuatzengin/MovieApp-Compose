package com.suatzengin.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.suatzengin.movieapp.screens.detail.DetailScreen
import com.suatzengin.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            // here we pass where this should lead us to
            HomeScreen(navController = navController)
        }
        composable(
            route = MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(
                navArgument(name = "movie") { type = NavType.StringType }
            )
        ) {
            val movie = it.arguments?.getString("movie")
            DetailScreen(navController = navController, movieId = movie)
        }

    }
}