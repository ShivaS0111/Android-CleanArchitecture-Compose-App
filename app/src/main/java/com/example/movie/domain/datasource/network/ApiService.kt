package com.example.movie.domain.datasource.network

import com.example.movie.domain.datasource.local.entities.Movie
import retrofit2.Response

interface ApiService {
    suspend fun getTvShows():Response<List<Movie>>
}