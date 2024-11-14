package com.example.movie.data.datasource.network.apiclient

import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.datasource.network.ApiService
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceImpl : ApiService {

    @GET("shows")
    override suspend fun getTvShows(): Response<List<Movie>>

}