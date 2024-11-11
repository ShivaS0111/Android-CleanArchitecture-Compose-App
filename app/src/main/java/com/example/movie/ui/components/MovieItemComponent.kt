package com.example.movie.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import com.example.movies.R
import com.invia.domain.datasource.database.entities.Movie

@Composable
fun MovieItem(
    movie: Movie,
    onClick: (movie: Movie) -> Unit,
    onDeleteClick: ((movie: Movie) -> Unit)? = null
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .wrapContentSize(),
        onClick = {
            onClick.invoke(movie)
        }
    ) {
        Box {
            Column {
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
                            .testTag(movie.id?.toString() ?: "lwlkd")
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
            }
            onDeleteClick?.let {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .background(color = Color.DarkGray.copy(alpha = 0.8f), shape = CircleShape)
                        .padding(5.dp)
                        .size(20.dp),
                    onClick = { it.invoke(movie) }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Green
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    //ShowItem(showsResponseItem = )
}