package com.example.movie.domain.datasource.local

import com.example.movie.data.datasource.local.dao.MovieDAO
import com.example.movie.data.datasource.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    suspend fun insert(movies: List<MovieEntity>)

    fun getAllMovies(): Flow<List<MovieEntity>>

    fun getMovieById(id: Int): Flow<MovieEntity>

    suspend fun getMovieDeleteById(movie: MovieEntity):Int
}