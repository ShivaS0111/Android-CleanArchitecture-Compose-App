package com.invia.data.useCases

import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.repository.MoviesRepository
import com.invia.domain.useCases.GetMoviesUseCase
import com.invia.domain.useCases.MovieDetailsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsUseCaseImpl @Inject constructor(private val repository: MoviesRepository) :
    MovieDetailsUseCase {
    override suspend fun invoke(movieId: Int) = repository.getMovieDetails(movieId)

}