package com.example.movie.di

import com.invia.data.datasource.MoviesDataSourceImpl
import com.invia.data.datasource.database.datasource.MoviesLocalDataSourceImpl
import com.invia.data.datasource.network.datasource.MoviesNetworkDataSourceImpl
import com.invia.domain.datasource.database.dao.MovieDAO
import com.invia.domain.datasource.database.datasource.MoviesLocalDataSource
import com.invia.domain.datasource.network.ApiService
import com.invia.domain.datasource.MoviesDataSource
import com.invia.domain.datasource.network.datasource.MoviesNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideMoviesDataSource(
        network: MoviesNetworkDataSource,
        local: MoviesLocalDataSource
    ): MoviesDataSource =
        MoviesDataSourceImpl(network, local)

    @Provides
    fun provideMoviesNetworkDataSource(apiService: ApiService): MoviesNetworkDataSource =
        MoviesNetworkDataSourceImpl(apiService)

    @Provides
    fun provideMoviesDatabaseDataSource(dao: MovieDAO): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl(dao)

}