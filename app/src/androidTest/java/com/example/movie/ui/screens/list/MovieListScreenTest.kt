package com.example.movie.ui.screens.list

import androidx.compose.ui.platform.LocalContext
import com.example.movie.core.util.UIStateHolder
import kotlinx.coroutines.flow.MutableStateFlow
import org.mockito.Mockito.verify
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movie.core.util.Result
import com.example.movie.domain.model.Movie
import com.example.movie.domain.usecases.DeleteMovieUseCase
import com.example.movie.domain.usecases.GetMoviesUseCase
import com.example.movie.ui.components.LoaderComponent
import com.example.movie.ui.components.MovieItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class MovieListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun movieItemTest(){
        composeTestRule.setContent {
            MovieItem(movie = Movie(1,null, null, "Movie1"), onClick = {})
        }
        
        composeTestRule.onNodeWithText("Movie1")
            .assertIsDisplayed()
    }

    @Test
    fun LoaderTest(){
        composeTestRule.setContent {
            LoaderComponent()
        }

        composeTestRule.onNodeWithTag("loader")
            .assertIsDisplayed()
    }

    @Test
    fun MovieListTest(){
        composeTestRule.setContent {
            MovieListScreenMainContent( UIStateHolder.Success(movies), onClick = {})
        }
        composeTestRule.onNodeWithTag("error")
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithTag("loader")
            .assertIsNotDisplayed()
        composeTestRule.onAllNodesWithTag("movie").assertCountEquals(3)
    }

    val movies = arrayListOf(
        Movie(id = 1, name = "Movie 1", image = null, language = null),
        Movie(id = 2, name = "Movie 2", image = null, language = null),
        Movie(id = 3, name = "Movie 3", image = null, language = null)
    )
}
