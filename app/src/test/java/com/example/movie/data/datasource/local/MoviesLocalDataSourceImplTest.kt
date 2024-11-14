package com.example.movie.data.datasource.local

import com.example.movie.domain.datasource.local.dao.MovieDAO
import com.example.movie.domain.datasource.local.entities.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
class MoviesLocalDataSourceImplTest {

    @Mock
    private lateinit var movieDAO: MovieDAO

    @InjectMocks
    private lateinit var localDataSource: MoviesLocalDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `insert should call dao insert with list of movies`() = runTest {
        val movies = listOf(
            Movie(id = 1, name = "Movie 1", image = null, language = null),
            Movie(id = 2, name = "Movie 2", image = null, language = null),
            Movie(id = 3, name = "Movie 3", image = null, language = null)
        )
        localDataSource.insert(movies)
        verify(movieDAO).insert(movies)
    }

    @Test
    fun `getAllMovies should return flow of movies from dao`() = runTest {
        val movies = listOf(Movie(id = 1, name = "Movie 1", image = null, language = null))
        `when`(movieDAO.getAllMovies()).thenReturn(flowOf(movies))

        val result = localDataSource.getAllMovies().first()

        assertEquals(movies, result)
        verify(movieDAO).getAllMovies()
    }

    @Test
    fun `getMovieById should return flow of movie from dao`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        `when`(movieDAO.getMovieById(1)).thenReturn(flowOf(movie))

        val result = localDataSource.getMovieById(1).first()

        assertEquals(movie, result)
        verify(movieDAO).getMovieById(1)
    }

    // 4. Test getMovieDeleteById when deletion is successful
    @Test
    fun `getMovieDeleteById should return the number of rows deleted`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        `when`(movieDAO.delete(movie)).thenReturn(1)

        val result = localDataSource.getMovieDeleteById(movie)

        assertEquals(1, result)
        verify(movieDAO).delete(movie)
    }
}
