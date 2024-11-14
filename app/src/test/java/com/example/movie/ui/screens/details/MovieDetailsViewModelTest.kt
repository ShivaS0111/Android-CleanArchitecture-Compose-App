package com.example.movie.ui.screens.details

import com.example.movie.core.util.Result
import com.example.movie.domain.model.Movie
import com.example.movie.domain.usecases.GetMovieDetailsUseCase

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
import org.mockito.Mock

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel

    lateinit var useCase: GetMovieDetailsUseCase

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = mockk()
        viewModel = MovieDetailsViewModel(useCase)
    }

    @Test
    fun `getMovie emits`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        coEvery { useCase.invoke(movie.id!!)} returns flow {
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
        coEvery { useCase.invoke(1)} returns flow {
            emit(Result.Error(errorMessage))
        }
        viewModel.getMovie(1)
        advanceUntilIdle()
        assertEquals(errorMessage, viewModel.response.value.error)
        assert(errorMessage == viewModel.response.value.error)
    }

}