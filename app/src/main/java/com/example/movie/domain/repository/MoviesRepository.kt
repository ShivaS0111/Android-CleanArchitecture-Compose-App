package com.example.movie.domain.repository

import com.example.movie.core.util.Result
import com.example.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getTvShows(): Flow<Result<List<Movie>>>

    suspend fun getMovieDetails(movieId: Int): Flow<Result<Movie>>

    suspend fun deleteMovie(movie: Movie): Flow<Result<String>>
}