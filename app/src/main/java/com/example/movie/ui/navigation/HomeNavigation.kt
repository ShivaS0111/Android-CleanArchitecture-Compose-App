package com.example.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.screens.details.MovieDetailsScreen
import com.example.movie.ui.screens.list.MovieListScreen

@Composable
fun HomeNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Navigate.MovieList.route) {
        composable(Navigate.MovieList.route) {
            MovieListScreen(navHostController = navController)
        }
        composable(
            Navigate.MovieDetails.route.plus("/{id}"),
            arguments = Navigate.MovieDetails.arguments!!
        ) {
            val id = it.arguments?.getInt("id")
            if (id != null) {
                MovieDetailsScreen( movieId = id)
            }
        }
    }
}