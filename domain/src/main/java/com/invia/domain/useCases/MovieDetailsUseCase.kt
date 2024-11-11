package com.invia.domain.useCases

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDetailsUseCase {
    suspend fun invoke(movieId: Int): Flow<Result<Movie>>
}