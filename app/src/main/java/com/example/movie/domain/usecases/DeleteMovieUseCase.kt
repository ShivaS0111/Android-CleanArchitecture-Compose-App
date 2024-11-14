package com.example.movie.domain.usecases

import com.example.movie.domain.model.Movie
import com.example.movie.domain.repository.MoviesRepository

class DeleteMovieUseCase(val repository: MoviesRepository) {
    suspend fun invoke(movie: Movie) = repository.deleteMovie(movie)
}