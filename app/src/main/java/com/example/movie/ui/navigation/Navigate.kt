package com.example.movie.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Navigate(
    val route: String,
    val title: String,
    val arguments: List<NamedNavArgument>? = emptyList()
) {

    companion object {
        const val movieList = "/movies"
        const val movieDetails = "/movie-details"
    }

    data object MovieList : Navigate(movieList, "Movies")
    data object MovieDetails : Navigate(
        route = movieDetails,
        title = "Movie Details",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )
}