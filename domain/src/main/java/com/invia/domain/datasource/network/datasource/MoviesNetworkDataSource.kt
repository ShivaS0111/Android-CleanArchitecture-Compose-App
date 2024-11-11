package com.invia.domain.datasource.network.datasource

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie

interface MoviesNetworkDataSource : BaseNetworkDataSource {
    suspend fun getTvShows(): Result<List<Movie>>
}