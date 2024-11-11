package com.example.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.screens.movies.details.MovieDetailsScreen
import com.example.movie.ui.screens.movies.list.MovieListScreen

@Composable
fun HomeNavigation(onAuthFailed: (route: String, data: Any?) -> Unit) {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Navigate.MovieList.route) {

        composable(Navigate.MovieList.route) {
            MovieListScreen(navHostController = navHostController)
        }

        composable(
            Navigate.MovieDetails.route.plus("/{id}"),
            arguments = Navigate.MovieDetails.arguments!!
        ) {
            val id = it.arguments?.getInt("id")
            if (id != null) {
                MovieDetailsScreen(navHostController = navHostController, movieId = id)
            }
        }


    }
}