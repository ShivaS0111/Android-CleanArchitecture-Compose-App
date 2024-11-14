package com.example.movie.data.datasource.local.dbclient

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movie.data.datasource.local.dao.MovieDAO
import com.example.movie.data.datasource.local.entities.MovieEntity
import com.example.movie.core.util.RoomDataTypeConverters

internal const val dbFileName = "movies.db"
@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1
)
@TypeConverters(RoomDataTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDAO(): MovieDAO

}