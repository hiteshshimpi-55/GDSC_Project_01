package com.example.note_itmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
class Note(@ColumnInfo(name = "text")val note:String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}