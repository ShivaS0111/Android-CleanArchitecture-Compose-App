package com.example.movie.di

import com.invia.data.repository.MoviesRepositoryImpl
import com.invia.domain.datasource.database.datasource.MoviesLocalDataSource
import com.invia.domain.datasource.network.datasource.MoviesNetworkDataSource
import com.invia.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoviesRepository(
        network: MoviesNetworkDataSource,
        local: MoviesLocalDataSource
    ): MoviesRepository = MoviesRepositoryImpl(network, local)

}