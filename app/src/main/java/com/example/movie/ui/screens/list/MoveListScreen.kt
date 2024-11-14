package com.example.movie.ui.screens.list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.ui.components.LoaderComponent
import com.example.movie.ui.components.MovieItem
import com.example.movie.ui.navigation.Navigate
import com.example.movie.ui.stateHolders.StateHolder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListScreen(
    navHostController: NavHostController,
    viewModel: MovieListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val result = viewModel.response.collectAsStateWithLifecycle()

    val pullRefreshState = rememberPullRefreshState(isRefreshing, {
        viewModel.setRefreshing(true)
        viewModel.getAllTvShows()
    })

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),

        ) {
        when (result.value) {
            is StateHolder.Loading -> LoaderComponent()
            is StateHolder.Success, is StateHolder.Error -> {
                if (result.value is StateHolder.Success) {
                    MovieListScreenMainContent(result.value as StateHolder.Success<List<Movie>>, onClick =  { movie ->
                        navHostController.navigate(Navigate.MovieDetails.route.plus("/${movie.id}"))
                    }, onDeleteClick = {
                        viewModel.onDeleteClick(it)
                    })
                } else {
                    ShowErrorComponent(result.value as StateHolder.Error<List<Movie>>)
                }
                PullRefreshIndicator(
                    refreshing = isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getAllTvShows()
        }

        viewModel.toastEventFlow.collectLatest {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

}


@Composable
fun ShowErrorComponent(error: StateHolder.Error<List<Movie>>) {
    if (error.error?.isNotBlank() == true) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = error.error)
        }
    }
}


@Composable
fun MovieListScreenMainContent(
    data: StateHolder.Success<List<Movie>>, onClick: (Movie) -> Unit,
    onDeleteClick: ((movie: Movie) -> Unit)? = null
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .testTag("list"),
        verticalItemSpacing = 10.dp, // Vertical spacing
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        data.data?.let { it: List<Movie> ->
            items(it.size) { index ->
                MovieItem(movie = it[index], onClick = onClick, onDeleteClick = onDeleteClick)
            }
        }
    }
}
