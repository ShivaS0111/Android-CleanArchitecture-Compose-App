package com.example.movie.presentation.viewModels
import com.example.movie.ui.screens.movies.list.MovieListViewModel
import com.example.movie.ui.stateHolders.StateHolder
import com.invia.data.repository.MoviesRepositoryImpl
import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @MockK
    private lateinit var viewModel: MovieListViewModel

    @MockK
    private lateinit var repository: MoviesRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate) // Set the main dispatcher to the test dispatcher
        //viewModel = MoviesViewModel( GetMoviesUseCaseImpl(repository))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testUseCaseSuccess() = runTest {

        val data: List<Movie> by lazy {
            arrayListOf(
                Movie(id = 1, name = "Movie 1", image = null, language = null),
                Movie(id = 2, name = "Movie 2", image = null, language = null),
                Movie(id = 3, name = "Movie 3", image = null, language = null)
            )
        }

        coEvery { viewModel.useCase.invoke() } returns flow {
            emit(
                Result.Success(
                    data
                )
            )
        }

        val response = MutableStateFlow<StateHolder<List<Movie>?>>(StateHolder.Loading())
        val job = launch {
            viewModel.useCase.invoke().collect {
                println("==>collect: $it")
                response.value = when (it) {
                    is Result.Loading -> StateHolder.Loading()
                    is Result.Success<*> -> StateHolder.Success(data = it.data)
                    is Result.Error -> StateHolder.Error(error = it.message.toString())
                }
            }
        }
        advanceUntilIdle()
        println("==>data: ${response.value.data}")
        assertEquals(data.size, response.value.data!!.size)
        assertEquals(data.first().id, response.value.data!!.first().id)

        job.cancel()
    }
    @Test
    fun testUseCaseLoading() = runTest {
       /* val loading = Result.Loading<List<Movie>>()
        coEvery { viewModel.useCase.invoke() } returns flow {
            emit(loading )
        }

        val response = MutableStateFlow(StateHolder<Movie>())
        val job = launch {
            viewModel.useCase.invoke().collect {
                println("==>collect: $it")
                response.value = when (it) {
                    is Result.Loading -> StateHolder(isLoading = true)
                    is Result.Success<*> -> StateHolder(data = it.data)
                    is Result.Error -> StateHolder(error = it.message.toString())
                }
            }
        }
        advanceUntilIdle()
        println("==>showLoader: ${response.value.isLoading}")
        assert( response.value.isLoading)
        job.cancel()*/
    }
    @Test
    fun testUseCaseException() = runTest {
        /*val exception = Result.Error<List<Movie>>("No movies found")
        coEvery { viewModel.useCase.invoke() } returns flow {
            emit(exception )
        }

        val response = MutableStateFlow(StateHolder<Movie>())
        val job = launch {
            viewModel.useCase.invoke().collect {
                println("==>collect: $it")
                response.value = when (it) {
                    is Result.Loading -> StateHolder(isLoading = true)
                    is Result.Success<*> -> StateHolder(data = it.data)
                    is Result.Error -> StateHolder(error = it.message.toString())
                }
            }
        }
        advanceUntilIdle()
        println("==>data: ${response.value.error}")
        assertEquals(exception.message, response.value.error)
        job.cancel()*/
    }
}