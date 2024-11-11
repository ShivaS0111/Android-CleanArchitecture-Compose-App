package com.invia.data.datasource.network

import com.invia.domain.datasource.network.ApiService
import com.invia.domain.datasource.database.entities.Movie
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceImpl : ApiService {

    @GET(Constants.END_POINT)
    override suspend fun getTvShows(): Response<List<Movie>>

}