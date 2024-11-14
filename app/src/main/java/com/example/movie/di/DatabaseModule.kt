package com.example.movie.di

import android.content.Context
import androidx.room.Room
import com.example.movie.domain.datasource.local.AppDatabase
import com.example.movie.domain.datasource.local.dao.MovieDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideLocalDataBase( @ApplicationContext applicationContext: Context): AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "Movies"
    ).build()

    @Provides
    fun provideMoviesDAO( db:AppDatabase): MovieDAO = db.moviesDAO()

}