package com.invia.domain.datasource.database

import android.content.Context
import android.graphics.Color
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@Database(
    entities = [Label::class, Note::class, LabelledNotes::class],
    version = 1,
    exportSchema = false
)
abstract class TestDatabase : RoomDatabase() {
    abstract fun labelDao(): LabelDAO
    abstract fun notesDao(): NotesDAO
}

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {
    private lateinit var db: TestDatabase
    private lateinit var labelDao: LabelDAO
    private lateinit var notesDao: NotesDAO

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TestDatabase::class.java).build()
        labelDao = db.labelDao()
        notesDao = db.notesDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertAndGetLabel() = runBlocking {
        val label = Label(label = "Test Label", color = Color.BLUE, textColor = Color.WHITE)
        labelDao.insert(label)

        val labels = labelDao.getAllLabels().first()
        assertEquals(1, labels.size)
        assertEquals("Test Label", labels[0].label)
    }

    @Test
    fun testInsertAndGetNote() = runBlocking {
        val note = Note(title = "Test1", note = "Test Note", dateTime = Date())
        notesDao.insert(note)

        val notes = notesDao.getAllNotes().first()
        assertEquals(1, notes.size)
        assertEquals("Test Note", notes[0].note)
    }

    @Test
    fun testSearchLabelByTerm() = runBlocking {
        val label1 = Label(label = "Test Label", color = Color.BLUE, textColor = Color.WHITE)
        val label2 = Label(label = "Test Label", color = Color.BLUE, textColor = Color.WHITE)

        labelDao.insert(listOf(label1, label2))

        val searchTerm = "Work"
        val searchResult = labelDao.getLabelBySearchTerm(searchTerm).first()
        assertEquals(1, searchResult.size)
        assertEquals("Work", searchResult[0].label)
    }

    @Test
    fun testSearchNoteByTerm() = runBlocking {
        val note1 =
            Note(title = "Work", note = "Work meeting", dateTime = Date())
        val note2 = Note(
            title = "Grocery",
            note = "Grocery shopping",
            dateTime = Date()
        )
        notesDao.insert(listOf(note1, note2))

        val searchTerm = "Grocery"
        val searchResult = notesDao.getNotesBySearchTerm(searchTerm).first()
        assertEquals(1, searchResult.size)
        assertEquals("Grocery shopping", searchResult[0].note)
    }


    @Test
    fun testInsertLabelledNotes() = runBlocking {
        val note = Note(title = "Work", note = "Sample Note", dateTime = Date())
        val label = Label(label = "Test Label", color = Color.BLUE, textColor = Color.WHITE)

        notesDao.insert(note)
        labelDao.insert(label)

        /*val labelledNote = LabelledNotes(noteId = note.noteId, labelId = label.labelId)
        db.labelledNotesDao().insert(labelledNote)

        val labelledNotes = db.labelledNotesDao().getAllLabelledNotes().first()
        assertEquals(1, labelledNotes.size)
        assertEquals(note.noteId, labelledNotes[0].noteId)
        assertEquals(label.labelId, labelledNotes[0].labelId)*/
    }
}