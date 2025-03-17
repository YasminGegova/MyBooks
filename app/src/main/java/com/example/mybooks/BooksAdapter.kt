package com.example.mybooks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BooksAdapter(private val books: List<String>) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView = inflater.inflate(R.layout.book_entry, parent, false)
        return ViewHolder(bookView)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book: String = books[position]
        val textView : TextView = holder.itemView.findViewById(R.id.tvTitle)
        textView.text = book
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}