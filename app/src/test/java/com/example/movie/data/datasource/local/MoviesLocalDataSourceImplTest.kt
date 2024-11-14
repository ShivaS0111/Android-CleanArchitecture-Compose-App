package com.example.movie.data.datasource.local

import com.example.movie.data.datasource.local.dao.MovieDAO
import com.example.movie.data.mapper.toEntity
import com.example.movie.domain.model.Movie
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
        localDataSource.insert(movies.map { it.toEntity() })
        verify(movieDAO).insert(movies.map { it.toEntity() })
    }

    @Test
    fun `getAllMovies should return flow of movies from dao`() = runTest {
        val movies = listOf(Movie(id = 1, name = "Movie 1", image = null, language = null)).map { it.toEntity() }
        `when`(movieDAO.getAllMovies()).thenReturn(flowOf(movies))

        val result = localDataSource.getAllMovies().first()

        assertEquals(movies, result)
        verify(movieDAO).getAllMovies()
    }

    @Test
    fun `getMovieById should return flow of movie from dao`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null).toEntity()
        `when`(movieDAO.getMovieById(1)).thenReturn(flowOf(movie))

        val result = localDataSource.getMovieById(1).first()

        assertEquals(movie, result)
        verify(movieDAO).getMovieById(1)
    }


    @Test
    fun `getMovieDeleteById should return the number of rows deleted`() = runTest {
        val movie = Movie(id = 1, name = "Movie 1", image = null, language = null)
        `when`(movieDAO.delete(movie.toEntity())).thenReturn(1)

        val result = localDataSource.getMovieDeleteById(movie.toEntity())

        assertEquals(1, result)
        verify(movieDAO).delete(movie.toEntity())
    }
}
