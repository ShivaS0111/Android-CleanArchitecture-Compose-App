package com.invia.data.datasource.database.datasource

import com.invia.domain.datasource.database.dao.MovieDAO
import com.invia.domain.datasource.database.datasource.MoviesLocalDataSource
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(override var dao: MovieDAO) : MoviesLocalDataSource {

    override suspend fun insert(label: Movie) = dao.insert(label)

    override suspend fun insert(movies: List<Movie>) = dao.insert(movies)

    override fun getAllMovies() = dao.getAllMovies()

    override fun getMovieById(id: Int): Flow<Movie> = dao.getMovieById(id)

    override suspend fun getMovieDeleteById(movie: Movie) :Int {
        println("====> MoviesLocalDataSourceImpl  getMovieDeleteById  ${movie.id}, $movie")
        return dao.delete(movie)
    }

    override fun getMovieBySearch(term: String) = dao.getMovieBySearch(term)

    override fun getMovieBySearchTerm(term: String) = dao.getMovieBySearchTerm(term)

}