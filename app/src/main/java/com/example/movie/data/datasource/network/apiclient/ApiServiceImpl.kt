package com.example.movie.data.datasource.network.apiclient

import com.example.movie.data.datasource.local.entities.MovieEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("shows")
    suspend fun getTvShows(): Response<List<MovieEntity>>

}