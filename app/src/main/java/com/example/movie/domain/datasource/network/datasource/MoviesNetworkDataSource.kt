package com.example.movie.domain.datasource.network.datasource

import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.common.Result


interface MoviesNetworkDataSource : BaseNetworkDataSource {
    suspend fun getTvShows(): Result<List<Movie>>
}