package com.example.movie.data.datasource.network

import com.example.movie.core.util.Result
import com.example.movie.data.datasource.network.apiclient.ApiService
import com.example.movie.data.mapper.toEntity
import com.example.movie.domain.model.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MoviesNetworkDataSourceImplTest {

    @Mock
    private lateinit var apiService: ApiService

    @InjectMocks
    private lateinit var networkDataSource: MoviesNetworkDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getTvShows should return success result when apiService returns data`() = runTest {
        val movies = listOf(
            Movie(
                id = 1,
                name = "Movie 1",
                image = null,
                language = null
            )
        ).map { it.toEntity() }
        `when`(apiService.getTvShows()).thenReturn(Response.success(movies))
        val result = networkDataSource.getTvShows()

        assertEquals(movies, result.data)
        verify(apiService).getTvShows()
    }

    @Test
    fun `getTvShows should return error result when apiService throws an exception`() = runTest {
        val errorMessage = "Network error"
        `when`(apiService.getTvShows()).thenThrow(RuntimeException(errorMessage))
        val result = networkDataSource.getTvShows()

        assert(result is Result.Error)
        assertEquals(errorMessage, (result as Result.Error).message)
        verify(apiService).getTvShows()
    }
}
