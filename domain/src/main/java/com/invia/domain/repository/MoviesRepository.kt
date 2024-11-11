package com.invia.domain.repository

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    val data: Flow<List<Movie>>
    suspend fun getTvShows(): Flow<Result<List<Movie>>>

    suspend fun getMovieDetails(movieId: Int): Flow<Result<Movie>>

    suspend fun deleteMovie(movie: Movie): Flow<Result<String>>
}