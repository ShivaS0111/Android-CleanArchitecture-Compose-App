package com.invia.domain.datasource

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.datasource.network.datasource.BaseNetworkDataSource
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {

    val data: Flow<List<Movie>>
    suspend fun getTvShows(): Flow<Result<List<Movie>>>
}