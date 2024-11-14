package com.example.movie.data.datasource.network

import com.example.movie.core.util.Result
import com.example.movie.data.datasource.local.entities.MovieEntity
import com.example.movie.data.datasource.network.apiclient.ApiService
import com.example.movie.domain.datasource.network.MoviesNetworkDataSource
import javax.inject.Inject

class MoviesNetworkDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    MoviesNetworkDataSource {

    override suspend fun getTvShows(): Result<List<MovieEntity>> = getResult { apiService.getTvShows() }

}