package com.example.movie.data.datasource.network

import com.example.movie.domain.common.Result
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.datasource.network.ApiService
import com.example.movie.domain.datasource.network.datasource.MoviesNetworkDataSource
import javax.inject.Inject

class MoviesNetworkDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MoviesNetworkDataSource {

    override suspend fun getTvShows(): Result<List<Movie>> = getResult { apiService.getTvShows() }

}