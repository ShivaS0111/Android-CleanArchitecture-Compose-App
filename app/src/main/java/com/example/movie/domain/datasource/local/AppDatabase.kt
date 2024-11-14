package com.example.movie.domain.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movie.domain.datasource.local.dao.MovieDAO
import com.example.movie.domain.datasource.local.entities.Movie
import com.example.movie.domain.datasource.local.util.RoomDataTypeConverters

internal const val dbFileName = "notes.db"
@Database(
    entities = [
        Movie::class,
    ],
    version = 1
)
@TypeConverters(RoomDataTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MovieDAO

}