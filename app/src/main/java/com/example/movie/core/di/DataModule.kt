package com.example.movie.core.di

import com.example.movie.data.datasource.local.MoviesLocalDataSourceImpl
import com.example.movie.data.datasource.network.MoviesNetworkDataSourceImpl
import com.example.movie.data.repository.MoviesRepositoryImpl
import com.example.movie.domain.datasource.local.MoviesLocalDataSource
import com.example.movie.data.datasource.local.dao.MovieDAO
import com.example.movie.data.datasource.network.apiclient.ApiService
import com.example.movie.domain.datasource.network.MoviesNetworkDataSource
import com.example.movie.domain.repository.MoviesRepository
import com.example.movie.domain.usecases.DeleteMovieUseCase
import com.example.movie.domain.usecases.GetMovieDetailsUseCase
import com.example.movie.domain.usecases.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

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


    @Provides
    fun provideGetMoviesUseCase(
        repository: MoviesRepository,
    ): GetMoviesUseCase = GetMoviesUseCase(repository)

    @Provides
    fun provideGetMovieDetailsUseCase(
        repository: MoviesRepository,
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCase(repository)

    @Provides
    fun provideDeleteMovieUseCase(
        repository: MoviesRepository,
    ): DeleteMovieUseCase = DeleteMovieUseCase(repository)

}