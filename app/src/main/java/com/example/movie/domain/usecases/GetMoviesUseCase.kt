package com.example.movie.domain.usecases

import com.example.movie.domain.repository.MoviesRepository

class GetMoviesUseCase(val repository: MoviesRepository) {
    suspend fun invoke() = repository.getTvShows()
}