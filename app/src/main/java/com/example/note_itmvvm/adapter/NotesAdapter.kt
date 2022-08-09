package com.example.note_itmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note_itmvvm.model.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter(private val context: Context,private val listener:INotesAdapter): RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview)
    {
        val textView:TextView
        val delete:ImageView

        init {
            textView = itemview.text
            delete   = itemview.deleteBtn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = MyViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item,parent,false))
        view.delete.setOnClickListener{
            listener.onItemClicked(allNotes[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentNote = allNotes[position]
        holder.textView.text = currentNote.note

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun onItemClicked(note: Note)
}