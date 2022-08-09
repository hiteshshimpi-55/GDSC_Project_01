package com.example.note_itmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_itmvvm.model.Note
import com.example.note_itmvvm.viewModel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesAdapter {

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                    .getInstance(application))[NoteViewModel::class.java]

        rv.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        rv.adapter = adapter

        viewModel.allNotes.observe(this, Observer {list->
            list?.let { adapter.updateList(it) }
        })

        button.setOnClickListener{
            val note = input.text.toString()
            if(!note.isEmpty()) {
                viewModel.insertNote(Note(note))
                Toast.makeText(this,"$note added to the list",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Cannot add empty item",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.note} deleted",Toast.LENGTH_SHORT).show()
    }
}