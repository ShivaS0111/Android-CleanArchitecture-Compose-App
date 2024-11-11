package com.invia.domain.useCases

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    val data: Flow<List<Movie>>
    suspend fun invoke(): Flow<Result<List<Movie>>>
}