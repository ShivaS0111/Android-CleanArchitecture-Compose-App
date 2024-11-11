package com.invia.domain.useCases

import com.invia.domain.common.Result
import com.invia.domain.datasource.database.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieDeleteUseCase {
    suspend fun invoke(movie: Movie): Flow<Result<String>>
}