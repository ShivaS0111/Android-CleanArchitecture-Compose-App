package com.invia.data.repository

import com.invia.domain.common.Result
import com.invia.domain.common.Result.Loading
import com.invia.domain.datasource.database.datasource.MoviesLocalDataSource
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.datasource.network.datasource.MoviesNetworkDataSource
import com.invia.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private var networkDatasource: MoviesNetworkDataSource,
    private var localDataSource: MoviesLocalDataSource
) : MoviesRepository {

    override val data = localDataSource.getAllMovies()

    override suspend fun getTvShows(): Flow<Result<List<Movie>>> = flow {
        networkDatasource.getTvShows().let {
            (it as Result.Success).data?.let { data ->
                localDataSource.insert(data)
            }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<Result<Movie>> = flow {
        try {
            val movie = localDataSource.getMovieById(movieId).first()
            emit(Result.Success(movie))
        } catch (e: Exception) {
            emit(Result.Error(e.message))  // Emit an error result if something goes wrong
        }
    }

    override suspend fun deleteMovie(movie: Movie)= flow {
        println("====> MoviesRepositoryImpl  deleteMovie $movie")
        try {
            val id = localDataSource.getMovieDeleteById(movie)
            if(id>0) {
                emit(Result.Success("Success"))
            }else{
                emit(Result.Error("failed to delete ${movie.id}"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message))  // Emit an error result if something goes wrong
        }
    }
}

class MockMoviesRepository @Inject constructor(private var mockData: List<Movie>) :
    MoviesRepository {

    override val data: Flow<List<Movie>> = flow { emit(mockData) }

    override suspend fun getTvShows() = flow {
        emit(Loading())
        kotlinx.coroutines.delay(2000)
        try {
            val result = Result.Success(mockData)
            emit(result)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override suspend fun getMovieDetails(movieId: Int) = flow {
        emit(Loading())
        kotlinx.coroutines.delay(2000)
        try {
            val filter = mockData.filter { it.id == movieId }
            val result = Result.Success(filter.first())
            emit(result)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override suspend fun deleteMovie(movie: Movie)= flow {
        emit(Loading())
        kotlinx.coroutines.delay(2000)
        try {
            val filter = mockData.filter { it.id != movie.id }
            mockData = filter
            val result = Result.Success("Success")
            emit(result)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

}