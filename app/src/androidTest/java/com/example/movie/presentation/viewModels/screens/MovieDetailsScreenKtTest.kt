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
import com.invia.domain.datasource.database.entities.Movie
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
        movieListViewModel = MovieListViewModel(GetMoviesUseCaseImpl(MockMoviesRepository(mockData = data)))
    }

    @Test
    fun validate_topBar_title_visibility() {
        composeTestRule.onNodeWithText("Movie List")
            .assertIsDisplayed()
    }

    @Test
    fun testLoadingState() {
        composeTestRule.onNodeWithTag("Loading...").assertExists()
    }

    @Test
    fun testErrorState() {
        composeTestRule.onNodeWithText("Error fetching data").assertExists()
    }

    @Test
    fun testDataLoadedState() {
        /*composeRule.setContent {
            MovieListScreen(navHostController = rememberNavController(), viewModel = moviesViewModel)
        }*/
        composeTestRule.onNodeWithTag("Loading...").assertExists()
        //composeRule.onNodeWithTag("list").assertExists()
        composeTestRule.onAllNodesWithTag("ShowItem").assertCountEquals(3)
    }

    @Test
    fun testButtons() {
        val buttonTags = listOf("co", "join", "cancel", "cancelJoin")
        buttonTags.forEach { tag ->
            composeTestRule.onNodeWithText(tag).assertExists().performClick()
        }
    }
}