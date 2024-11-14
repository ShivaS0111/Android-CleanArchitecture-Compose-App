package com.example.movie.data.repository

import com.example.movie.domain.common.Result
import com.example.movie.domain.datasource.local.MoviesLocalDataSource
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.datasource.network.datasource.MoviesNetworkDataSource
import com.example.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private var networkDatasource: MoviesNetworkDataSource,
    private var localDataSource: MoviesLocalDataSource
) : MoviesRepository {

    suspend fun getTvShowsFromLocal(): Flow<List<Movie>> = localDataSource.getAllMovies()
    suspend fun getTvShowsFromNetwork(): Result<List<Movie>> = networkDatasource.getTvShows()

    override suspend fun getTvShows(): Flow<Result<List<Movie>>> = channelFlow {
        async {
            val data = getTvShowsFromLocal().first()
            if (data.isEmpty()) {
                send(Result.Success(data))
            }
        }
        async {
            getTvShowsFromNetwork().let {
                if (it is Result.Success && it.data?.isNotEmpty() == true) {
                    localDataSource.insert(it.data)
                }
                send(it)
            }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Result<Movie>> =
        channelFlow {
            try {
                val movie = localDataSource.getMovieById(movieId).first()
                send(Result.Success(movie))
            } catch (e: Exception) {
                send(Result.Error(e.message))  // Emit an error result if something goes wrong
            }
        }

    override suspend fun deleteMovie(movie: Movie) = channelFlow {
        println("====> MoviesRepositoryImpl  deleteMovie $movie")
        try {
            val id = localDataSource.getMovieDeleteById(movie)
            if (id > 0) {
                send(Result.Success("Success"))
            } else {
                send(Result.Error("failed to delete ${movie.id}"))
            }
        } catch (e: Exception) {
            send(Result.Error(e.message))  // Emit an error result if something goes wrong
        }
    }
}

class MockMoviesRepository @Inject constructor(private var mockData: List<Movie>) :
    MoviesRepository {

    override suspend fun getTvShows() = channelFlow {
        send(Result.Loading())
        kotlinx.coroutines.delay(2000)
        try {
            val result = Result.Success(mockData)
            send(result)
        } catch (e: Exception) {
            send(Result.Error(e.message.toString()))
        }
    }

    override suspend fun getMovieDetails(movieId: Int) = channelFlow {
        send(Result.Loading())
        kotlinx.coroutines.delay(2000)
        try {
            val filter = mockData.filter { it.id == movieId }
            val result = Result.Success(filter.first())
            send(result)
        } catch (e: Exception) {
            send(Result.Error(e.message.toString()))
        }
    }

    override suspend fun deleteMovie(movie: Movie) = channelFlow {
        send(Result.Loading())
        kotlinx.coroutines.delay(2000)
        try {
            val filter = mockData.filter { it.id != movie.id }
            mockData = filter
            val result = Result.Success("Success")
            send(result)
        } catch (e: Exception) {
            send(Result.Error(e.message.toString()))
        }
    }

}