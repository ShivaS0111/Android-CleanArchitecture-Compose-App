package com.invia.domain.datasource.network

import com.invia.domain.datasource.database.entities.Movie
import retrofit2.Response

interface ApiService {
    suspend fun getTvShows():Response<List<Movie>>
}