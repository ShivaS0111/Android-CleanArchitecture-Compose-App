package com.example.movie.domain.usecases

import com.example.movie.domain.repository.MoviesRepository

class GetMovieDetailsUseCase(val repository: MoviesRepository) {
    suspend fun invoke(movieId: Int) = repository.getMovieDetails(movieId)
}