package com.invia.domain.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.invia.domain.datasource.database.dao.MovieDAO
import com.invia.domain.datasource.database.entities.Movie

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