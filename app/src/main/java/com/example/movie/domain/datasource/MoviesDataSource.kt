package com.example.movie.domain.datasource

import com.example.movie.domain.common.Result
import com.example.movie.domain.datasource.local.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    val data: Flow<List<Movie>>
    suspend fun getTvShows(): Flow<Result<List<Movie>>>
}