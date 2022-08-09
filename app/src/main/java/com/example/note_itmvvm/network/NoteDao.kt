package com.example.note_itmvvm.network

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.note_itmvvm.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAll(): LiveData<List<Note>>

}