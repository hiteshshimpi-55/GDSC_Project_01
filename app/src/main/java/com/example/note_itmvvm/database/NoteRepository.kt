package com.example.note_itmvvm.database

import androidx.lifecycle.LiveData
import com.example.note_itmvvm.model.Note
import com.example.note_itmvvm.network.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAll()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

 }