package com.invia.data.datasource.network.datasource

import com.aqube.ram.remote.utils.RemoteBaseTest
import com.invia.data.fake.FakeData
import com.invia.domain.datasource.network.ApiService
import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.datasource.network.datasource.MoviesNetworkDataSource
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesNetworkDataSourceImplTest : RemoteBaseTest() {

    private lateinit var apiService: ApiService
    private lateinit var fakeData: FakeData
    private lateinit var moviesDataSource: MoviesNetworkDataSource

    @Before
    fun setUp() {
        fakeData = FakeData()
        apiService = mockk()
        moviesDataSource = MoviesNetworkDataSourceImpl(apiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get tv shows from server1`() = runTest{
            val response: Result<List<Movie>> = fakeData.getDataSourceTvShows()

            val data = async {
                Mockito.`when`(moviesDataSource.getTvShows().data)
                    .thenReturn (response.data)
            }
        moviesDataSource.getTvShows()
            advanceUntilIdle() // Yields to perform the registrations
            TestCase.assertEquals(response, data.await())
        }

    @Test
    fun `get tv shows from server`(): Unit =
        runTest(timeout = 3000.seconds) {
            // Arrange (Given)
            //val response: Result<List<ShowsResponseItem>> = fakeData.getDataSourceTvShows()
            //coEvery { moviesDataSource.getTvShows() } returns response

            val response = runBlocking { moviesDataSource.getTvShows() }

            coVerify { apiService.getTvShows() }
            val loaded = moviesDataSource.getTvShows()
            println("======================")
            println(loaded.data)
            println(loaded.message)
            println("---------------------")
            println(response.data)
            println(response.message)
            println("======================")

            assertEquals(response, loaded)
        }

    @After
    fun tearDown() {
    }

}
