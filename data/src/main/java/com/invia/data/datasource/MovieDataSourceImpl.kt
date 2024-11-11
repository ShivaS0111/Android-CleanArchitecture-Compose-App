package com.invia.data.datasource

import com.invia.domain.common.Result
import com.invia.domain.datasource.MoviesDataSource
import com.invia.domain.datasource.database.datasource.MoviesLocalDataSource
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.datasource.network.datasource.MoviesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesDataSourceImpl @Inject constructor(
    private var networkDatasource: MoviesNetworkDataSource,
    private var localDataSource: MoviesLocalDataSource
) : MoviesDataSource {

    override val data: Flow<List<Movie>>
        get() = localDataSource.getAllMovies()

    override suspend fun getTvShows(): Flow<Result<List<Movie>>> = flow {
        emit(Result.Loading())
        networkDatasource.getTvShows().let { result ->
            if (result is Result.Success) {
                println("==>insert: ${result.data?.size}")
                result.data?.let { localDataSource.insert(it) }
            } else {
                emit(result)
            }
        }
    }.catch {
        println("===> e: ${it.message}")
        emit(Result.Error(it.message))
    }

}