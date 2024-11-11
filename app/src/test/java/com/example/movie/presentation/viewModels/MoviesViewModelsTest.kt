import com.example.movie.ui.screens.movies.list.MovieListViewModel
import com.invia.data.repository.MockMoviesRepository
import com.invia.data.repository.MoviesRepositoryImpl
import com.invia.data.useCases.GetMoviesUseCaseImpl
import com.invia.data.useCases.MovieDeleteUseCaseImpl

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

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
            mockk(), mockk()
        )
    }

    @Test
    fun `getAllTvShows emits`() = runTest {

        coEvery { viewModel.useCase.data } returns flow {
            emit(emptyList())
        }

        coEvery { viewModel.useCase.invoke() } returns  flow {
            emit(Result.Success(data))
        }

        viewModel.getAllTvShows()
        advanceUntilIdle()
        println(viewModel.useCase.data.first())
        println(viewModel.response.value.data?.first())
        val respData =viewModel.response.value.data;
        assertEquals(data, respData)
    }

    @Test
    fun `getAllTvShows emits error states`() = runTest {

        coEvery { viewModel.useCase.data } returns flow {
            emit(emptyList())
        }

        val errorMessage = "Network error"
        coEvery { viewModel.useCase.invoke() } returns flow {
            emit(Result.Error(errorMessage))
        }
        viewModel.getAllTvShows()
        advanceUntilIdle()

        println(viewModel.useCase.data.first())
        println(viewModel.response.value.error)
        assert(viewModel.response.value.error != null)

        assert(errorMessage == viewModel.response.value.error)
    }

}