package com.example.movie.presentation.viewModels.screens

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.movie.MainActivity
import com.example.movie.ui.screens.movies.list.MovieListViewModel
import com.invia.data.repository.MockMoviesRepository
import com.invia.data.useCases.GetMoviesUseCaseImpl
import com.invia.data.useCases.MovieDeleteUseCaseImpl
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailsScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    private lateinit var movieListViewModel: MovieListViewModel

    @Before
    fun before() {
        val data = arrayListOf<Movie>()
        data.add(Movie(id = 1, name = "Movie 1", image = null, language = null))
        data.add(Movie(id = 2, name = "Movie 2", image = null, language = null))
        data.add(Movie(id = 3, name = "Movie 3", image = null, language = null))

        val getMoviesUseCase = GetMoviesUseCaseImpl(MockMoviesRepository(mockData = data))
        val deleteMovieUseCase = MovieDeleteUseCaseImpl(MockMoviesRepository(mockData = data))

        movieListViewModel = MovieListViewModel(
            getMoviesUseCase, deleteMovieUseCase
        )
    }

    @Test
    fun testLoadingState() {
        composeTestRule.onNodeWithTag("Loading...").assertExists()
    }

}