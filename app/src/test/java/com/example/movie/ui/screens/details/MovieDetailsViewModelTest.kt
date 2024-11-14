package com.example.movie.ui.screens.details

import com.example.movie.domain.common.Result
import com.example.movie.domain.datasource.local.entities.Movie

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = MovieDetailsViewModel(
            mockk()
        )
    }

    @Test
    fun `getMovie emits`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        coEvery { viewModel.repository.getMovieDetails(1)} returns flow {
            emit(Result.Success(movie))
        }
        viewModel.getMovie(1)
        advanceUntilIdle()
        println(viewModel.response.value.data)
        val respData =viewModel.response.value.data
        assertEquals(movie, respData)
    }

    @Test
    fun `getMovie emits error states`() = runTest {

        val errorMessage = "Network error"
        coEvery { viewModel.repository.getMovieDetails(1)} returns flow {
            emit(Result.Error(errorMessage))
        }
        viewModel.getMovie(1)
        advanceUntilIdle()
        assertEquals(errorMessage, viewModel.response.value.error)
        assert(errorMessage == viewModel.response.value.error)
    }

}