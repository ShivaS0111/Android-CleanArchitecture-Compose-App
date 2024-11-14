package com.example.movie.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.ui.components.LoaderComponent
import com.example.movie.ui.stateHolders.StateHolder
import com.example.movies.R
import kotlinx.coroutines.launch

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Int,
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val movie by viewModel.response.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getMovie(movieId)
        }
    }

    Box(Modifier.fillMaxSize()) {
        when(movie){
            is StateHolder.Loading -> LoaderComponent()
            is StateHolder.Success -> {
                (movie as StateHolder.Success<Movie>).data?.let { MovieItem(it) }
            }
            is StateHolder.Error -> {
                Text(text = (movie as StateHolder.Error<Movie>).error ?: "Error")
            }
        }
    }
}


@Composable
fun MovieItem(movie: Movie) {
    Column( Modifier.fillMaxSize()) {
        movie.image?.let {
            Image(
                painter = rememberAsyncImagePainter(
                    model = it.original,
                    imageLoader = ImageLoader.Builder(LocalContext.current)
                        .placeholder(R.drawable.placeholder).crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED).build()
                ),
                contentScale = ContentScale.FillHeight,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .aspectRatio(matchHeightConstraintsFirst = false, ratio = 0.68f)
                    .testTag(movie.id?.toString() ?: "movie item")
                    .then((painter as? AsyncImagePainter)?.let { it.state as? AsyncImagePainter.State.Success }?.painter?.intrinsicSize?.let { intrinsicSize ->
                        Modifier.aspectRatio(intrinsicSize.width / intrinsicSize.height)
                    } ?: Modifier),
            )
        }
        Text(
            text = movie.name ?: "empty",
            Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Row {
            Text(
                text = movie.language ?: "empty",
                Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}

