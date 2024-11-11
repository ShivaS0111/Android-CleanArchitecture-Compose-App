package com.example.movie.di

import com.invia.data.useCases.GetMoviesUseCaseImpl
import com.invia.data.useCases.MovieDeleteUseCaseImpl
import com.invia.data.useCases.MovieDetailsUseCaseImpl
import com.invia.domain.repository.MoviesRepository
import com.invia.domain.useCases.GetMoviesUseCase
import com.invia.domain.useCases.MovieDeleteUseCase
import com.invia.domain.useCases.MovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideMoviesUseCase(repository: MoviesRepository): GetMoviesUseCase =
        GetMoviesUseCaseImpl(repository)

    @Provides
    fun provideMovieDetailsUseCase(repository: MoviesRepository): MovieDetailsUseCase =
        MovieDetailsUseCaseImpl(repository)

    @Provides
    fun provideMovieDeleteUseCase(repository: MoviesRepository): MovieDeleteUseCase =
        MovieDeleteUseCaseImpl(repository)

}