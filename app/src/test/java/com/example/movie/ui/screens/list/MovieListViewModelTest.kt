package com.example.movie.ui.screens.list

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
class MovieListViewModelTest {

    private lateinit var viewModel: MovieListViewModel

    private val data: List<Movie> by lazy {
        arrayListOf(
            Movie(id = 1, name = "Movie 1", image = null, language = null),
            Movie(id = 2, name = "Movie 2", image = null, language = null),
            Movie(id = 3, name = "Movie 3", image = null, language = null)
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = MovieListViewModel(
            mockk()
        )
    }

    @Test
    fun `getAllTvShows emits`() = runTest {
        coEvery { viewModel.repository.getTvShows()} returns flow {
            emit(Result.Success(data))
        }
        viewModel.getAllTvShows()
        advanceUntilIdle()
        println(viewModel.response.value.data?.first())
        val respData =viewModel.response.value.data
        assertEquals(data, respData)
    }

    @Test
    fun `getAllTvShows emits error states`() = runTest {

        val errorMessage = "Network error"
        coEvery { viewModel.repository.getTvShows()} returns flow {
            emit(Result.Error(errorMessage))
        }

        viewModel.getAllTvShows()
        advanceUntilIdle()
        assertEquals(errorMessage, viewModel.response.value.error)
        assert(errorMessage == viewModel.response.value.error)
    }

}