package com.example.movie.domain.datasource.local

import com.example.movie.domain.datasource.local.dao.MovieDAO
import com.example.movie.domain.datasource.local.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    var dao: MovieDAO

    suspend fun insert(movies: List<Movie>)

    fun getAllMovies(): Flow<List<Movie>>

    fun getMovieById(id: Int): Flow<Movie>

    suspend fun getMovieDeleteById(movie: Movie):Int
}