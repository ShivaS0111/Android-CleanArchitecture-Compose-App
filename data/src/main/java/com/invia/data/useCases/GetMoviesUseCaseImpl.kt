package com.invia.data.useCases

import com.invia.domain.datasource.database.entities.Movie
import com.invia.domain.repository.MoviesRepository
import com.invia.domain.useCases.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(private val repository: MoviesRepository) :
    GetMoviesUseCase {

    override val data: Flow<List<Movie>> = repository.data
    override suspend operator fun invoke() = repository.getTvShows()
}