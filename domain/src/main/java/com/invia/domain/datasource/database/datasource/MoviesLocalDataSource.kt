package com.invia.domain.datasource.database.datasource

import com.invia.domain.datasource.database.dao.MovieDAO
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    var dao: MovieDAO

    suspend fun insert(label: Movie)

    suspend fun insert(movies: List<Movie>)

    fun getAllMovies(): Flow<List<Movie>>

    fun getMovieById(id: Int): Flow<Movie>

    suspend fun getMovieDeleteById(movie: Movie):Int

    fun getMovieBySearch(term: String): Flow<List<Movie>>

    fun getMovieBySearchTerm(term: String): Flow<List<Movie>>

}