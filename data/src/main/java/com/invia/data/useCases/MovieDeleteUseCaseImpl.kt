package com.invia.data.useCases

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.repository.MoviesRepository
import com.invia.domain.useCases.GetMoviesUseCase
import com.invia.domain.useCases.MovieDeleteUseCase
import com.invia.domain.useCases.MovieDetailsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDeleteUseCaseImpl @Inject constructor(private val repository: MoviesRepository) :
    MovieDeleteUseCase {

    override suspend fun invoke(movie: Movie) = repository.deleteMovie(movie)
}