package com.example.movie.ui.screens.splash

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movie.ui.components.LoaderComponent
import com.example.movie.ui.navigation.Navigate
import com.example.movie.ui.stateHolders.StateHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onAuthSuccess: ((Any?) -> Unit),
    onAuthFailure: ((route: String) -> Unit)? = null,
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isAuthenticating = viewModel.isUserAuthenticated.collectAsStateWithLifecycle()

    Box {
        if (isAuthenticating.value is StateHolder.Loading) {
            LoaderComponent()
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Movies App", style = MaterialTheme.typography.headlineLarge)

            Text(
                "Authentication Success",
                style = MaterialTheme.typography.labelMedium, textAlign = TextAlign.Center,

                modifier = Modifier.fillMaxWidth(
                    fraction = if (isAuthenticating.value is StateHolder.Success) 1.0f else 0.0f
                )
            )

        }

    }

    LaunchedEffect(isAuthenticating.value) {
        coroutineScope.launch {
            if (isAuthenticating.value is StateHolder.Success) {
                onAuthSuccess?.invoke(Navigate.movieList)
            } else if (isAuthenticating.value is StateHolder.Error) {
                //onAuthFailure?.invoke(Navigate.login)
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.authenticateUser()
        }
    }
}