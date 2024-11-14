package com.example.movie.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movie.data.datasource.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieEntity>)

    @Transaction
    @Delete
    suspend fun delete(movie: MovieEntity):Int

    @Transaction
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movies where id = (:id)")
    fun getMovieById(id: Int): Flow<MovieEntity>

}
