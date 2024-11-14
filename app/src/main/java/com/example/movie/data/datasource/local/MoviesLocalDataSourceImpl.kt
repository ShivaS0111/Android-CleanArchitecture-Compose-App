package com.example.movie.data.datasource.local

import com.example.movie.domain.datasource.local.MoviesLocalDataSource
import com.example.movie.data.datasource.local.dao.MovieDAO
import com.example.movie.data.datasource.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(override var dao: MovieDAO) :
    MoviesLocalDataSource {

    override suspend fun insert(movies: List<MovieEntity>) = dao.insert(movies)

    override fun getAllMovies() = dao.getAllMovies()

    override fun getMovieById(id: Int): Flow<MovieEntity> = dao.getMovieById(id)

    override suspend fun getMovieDeleteById(movie: MovieEntity) = dao.delete(movie)

}