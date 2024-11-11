import com.example.movie.ui.screens.movies.list.MovieListViewModel
import com.invia.data.repository.MockMoviesRepository
import com.invia.data.repository.MoviesRepositoryImpl
import com.invia.data.useCases.GetMoviesUseCaseImpl
import com.invia.data.useCases.MovieDeleteUseCaseImpl

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var repository: MoviesRepositoryImpl

    private val data: List<Movie> by lazy {
        arrayListOf(
            Movie(id = 1, name = "Movie 1", image = null, language = null),
            Movie(id = 2, name = "Movie 2", image = null, language = null),
            Movie(id = 3, name = "Movie 3", image = null, language = null)
        )
    }

    @Before
    fun setUp() {
        val getMoviesUseCase = GetMoviesUseCaseImpl(MockMoviesRepository(mockData = data))
        val deleteMovieUseCase = MovieDeleteUseCaseImpl(MockMoviesRepository(mockData = data))

        viewModel = MovieListViewModel(
            getMoviesUseCase, deleteMovieUseCase
        )
    }

    @Test
    fun `getAllTvShows emits`() {
        coEvery { viewModel.useCase.invoke() } returns  flow {
            emit(Result.Success(data))
        }

        viewModel.getAllTvShows()
        coVerify {
            viewModel.useCase.invoke()
        }
    }

    @Test
    fun `getAllTvShows emits loading and error states`() = runTest {
        val errorMessage = "Network error"
        coEvery { viewModel.useCase.invoke() } returns flow {
            emit(Result.Error(errorMessage))
        }
        viewModel.useCase.invoke()

        Assert.assertEquals(errorMessage, viewModel.response.value.error)
    }

}