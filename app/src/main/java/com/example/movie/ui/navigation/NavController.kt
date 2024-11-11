package com.example.movie.ui.navigation

import androidx.annotation.MainThread
import androidx.core.net.toUri
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination.Companion.createRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.navArgument

sealed class Navigate(
    val route: String,
    val title: String,
    val arguments: List<NamedNavArgument>? = emptyList()
){

    companion object {
        const val splash = "/"
        const val main = "/main"
        const val login = "/login"
        const val register = "/register"

        const val home = "/home"
        const val services = "/services"
        const val categories = "/categories"

        const val movieList = "/movies"
        const val movieDetails = "/movie-details"


        val startDestination = Splash
    }

    data object Main : Navigate(main, "Main")
    data object Splash : Navigate(splash, "Splash")
    data object MovieList : Navigate(movieList, "Movies")

    data object MovieDetails : Navigate(
        route = movieDetails,
        title = "Movie Details",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )

    data object Home : Navigate(home, title = "Home")
    data object Login : Navigate(login, title = "Login")
    data object Register : Navigate(register, title = "Register")
    data object Services : Navigate(services, title = "Services")
    data object Categories : Navigate(categories, title = "Categories")
}

fun NavHostController.navigateAndClean(route: String) {
    navigate(route = route) {
        graph.startDestinationRoute?.let { popUpTo(it) { inclusive = true } }
    }
    graph.setStartDestination(route)
}

@MainThread
@JvmOverloads
public fun NavHostController.navigateTo(
    route: String, navOptions: NavOptions? = null, navigatorExtras: Navigator.Extras? = null
) {
    navigate(route, navOptions, navigatorExtras)
}