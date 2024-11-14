package com.example.movie.data.repository

import com.example.movie.core.util.Result
import com.example.movie.data.mapper.toEntity
import com.example.movie.domain.datasource.local.MoviesLocalDataSource
import com.example.movie.domain.datasource.network.MoviesNetworkDataSource
import com.example.movie.domain.model.Movie
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MoviesRepositoryImplTest {

    @Mock
    private lateinit var networkDataSource: MoviesNetworkDataSource

    @Mock
    private lateinit var localDataSource: MoviesLocalDataSource

    @InjectMocks
    private lateinit var repository: MoviesRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getTvShows should emit empty result when both local and network data are empty`() = runTest {
        `when`(localDataSource.getAllMovies()).thenReturn(flowOf(emptyList()))
        `when`(networkDataSource.getTvShows()).thenReturn(Result.Success(emptyList()))

        val result = repository.getTvShows().first()

        println("===>res: $result, ${result.data}  ${result.message}  ")
        assertTrue(result is Result.Success && result.data?.isEmpty() == true)
    }

    @Test
    fun `getMovieDetails should emit success when movie exists in local data`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        `when`(localDataSource.getMovieById(1)).thenReturn(flowOf(movie.toEntity()))

        val result = repository.getMovieDetails(1).first()

        assertTrue(result is Result.Success && result.data == movie)
    }

    @Test
    fun `getMovieDetails should emit error when movie does not exist in local data`() = runTest {
        `when`(localDataSource.getMovieById(1)).thenReturn(flow { throw Exception("Movie not found") })

        val result = repository.getMovieDetails(1).first()

        assertTrue(result is Result.Error && result.message == "Movie not found")
    }

    @Test
    fun `deleteMovie should emit success when movie is successfully deleted`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        `when`(localDataSource.getMovieDeleteById(movie.toEntity())).thenReturn(1)
        val result = repository.deleteMovie(movie).first()

        println("===>res: $result, ${result.data}")
        assertTrue(result is Result.Success && result.data == "Success")
    }

    @Test
    fun `deleteMovie should emit error when movie deletion fails`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        `when`(localDataSource.getMovieDeleteById(movie.toEntity())).thenReturn(0)

        val result = repository.deleteMovie(movie).first()

        assertTrue(result is Result.Error && result.message == "failed to delete ${movie.id}")
    }
}
