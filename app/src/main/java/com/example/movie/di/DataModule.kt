package com.example.movie.di

import com.example.movie.data.datasource.local.MoviesLocalDataSourceImpl
import com.example.movie.data.datasource.network.MoviesNetworkDataSourceImpl
import com.example.movie.data.repository.MoviesRepositoryImpl
import com.example.movie.domain.datasource.local.MoviesLocalDataSource
import com.example.movie.domain.datasource.local.dao.MovieDAO
import com.example.movie.domain.datasource.network.ApiService
import com.example.movie.domain.datasource.network.datasource.MoviesNetworkDataSource
import com.example.movie.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMoviesNetworkDataSource(apiService: ApiService): MoviesNetworkDataSource =
        MoviesNetworkDataSourceImpl(apiService)

    @Provides
    fun provideMoviesDatabaseDataSource(dao: MovieDAO): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl(dao)

    @Provides
    fun provideMoviesRepository(
        network: MoviesNetworkDataSource,
        local: MoviesLocalDataSource
    ): MoviesRepository = MoviesRepositoryImpl(network, local)

}