package com.example.movie.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.movie.ui.components.AppHeader
import com.example.movie.ui.navigation.HomeNavigation

@Composable
fun App() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { AppHeader(navController) },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            HomeNavigation(navController)
        }
    }
}