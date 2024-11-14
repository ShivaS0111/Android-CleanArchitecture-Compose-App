package com.example.movie.core.di

import android.content.Context
import androidx.room.Room
import com.example.movie.data.datasource.local.dbclient.AppDatabase
import com.example.movie.data.datasource.local.dao.MovieDAO
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
    fun provideMoviesDAO( db: AppDatabase): MovieDAO = db.moviesDAO()

}