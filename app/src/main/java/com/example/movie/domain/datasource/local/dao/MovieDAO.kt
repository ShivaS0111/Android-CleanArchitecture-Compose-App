package com.example.movie.domain.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movie.domain.datasource.local.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(label: Movie)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Transaction
    @Delete
    suspend fun delete(movie: Movie):Int

    @Transaction
    @Query("delete from movies where id = (:id)")
    suspend fun delete(id: Int):Int

    @Transaction
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Transaction
    @Query("SELECT * FROM movies where id = (:id)")
    fun getMovieById(id: Int): Flow<Movie>

    @Transaction
    @Query("SELECT * FROM movies where name LIKE (:term)")
    fun getMovieBySearch(term: String): Flow<List<Movie>>

    @Transaction
    @Query("SELECT * FROM movies WHERE name LIKE '%' || :term || '%'")
    fun getMovieBySearchTerm(term: String): Flow<List<Movie>>

}
