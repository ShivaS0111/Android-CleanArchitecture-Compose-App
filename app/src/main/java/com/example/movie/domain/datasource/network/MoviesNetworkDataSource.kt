package com.example.movie.domain.datasource.network

import com.example.movie.data.datasource.local.entities.MovieEntity
import com.example.movie.core.util.Result
import com.example.movie.core.util.BaseNetworkDataSource


interface MoviesNetworkDataSource : BaseNetworkDataSource {
    suspend fun getTvShows(): Result<List<MovieEntity>>
}