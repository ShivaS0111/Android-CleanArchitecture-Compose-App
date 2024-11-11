package com.example.movie

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.invia.domain.datasource.database.dao.MovieDAO
import com.invia.domain.datasource.database.entities.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date


@Database(
    entities = [Movie::class,],
    version = 1,
    exportSchema = false
)
abstract class TestDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var db: TestDatabase
    private lateinit var movieDao: MovieDAO

    @Before
    fun createDb() {
        println("=========createDb start==========")
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TestDatabase::class.java).build()

        println("=========db created========== $db")
        movieDao = db.movieDAO()
        println("=========createDb end========== ")
    }

    @After
    fun closeDb() {
        println("=========closeDb start========== $db")
        db.close()
        println("=========closeDb end==========")
    }

    @Test
    fun testInsertAndGetLabel() = runBlocking {
        val movie1 = Movie(id = 1, name = "Movie 1", image = null, language = null)
        movieDao.insert(movie1)

        val movies = movieDao.getAllMovies().first()
        assertEquals(1, movies.size)
        assertEquals("Movie 1", movies[0].name)
    }


}
