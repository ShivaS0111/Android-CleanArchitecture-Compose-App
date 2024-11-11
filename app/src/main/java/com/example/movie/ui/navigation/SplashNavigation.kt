package com.example.movie.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movie.ui.screens.MainScreen
import com.example.movie.ui.screens.splash.SplashScreen

@Composable
fun SplashNavigation(
    authNavController: NavHostController,
) {
    NavHost(navController = authNavController, startDestination = Navigate.startDestination.route) {
        composable(Navigate.Splash.route) {
            SplashScreen(onAuthSuccess = {
                authNavController.navigateAndClean(Navigate.Main.route)
            },
                onAuthFailure = {
                    authNavController.navigateAndClean(Navigate.Login.route)
                })
        }
        composable(Navigate.Main.route) {
            MainScreen(onAuthFailed = { _, _ ->
                authNavController.navigateAndClean(Navigate.Login.route)
            })
        }

        /*composable(Route.Login.route) {
            LoginScreen(onAuthSuccess = {
                splashNavHostController.navigate("main")
            }, onAuthFailed = { route, data ->
            }, onNavigateTo = { route, data ->
                authNavController.navigateAndClean(route)
            }, onRegisterClick = { authNavController.navigateAndClean(Route.Register.route) })
        }
        composable(Route.Register.route) {
            RegisterScreen(onAuthSuccess = {
                authNavController.navigateAndClean(Route.Login.route)
            }, onAuthFailed = { route, data ->
            }, onNavigateTo = { route, data ->
                authNavController.navigateAndClean(route)
            })
        }*/
    }

}
