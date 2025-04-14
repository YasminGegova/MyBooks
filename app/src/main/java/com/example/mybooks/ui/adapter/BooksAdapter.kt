package com.example.mybooks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.database.model.BookData

class BooksAdapter(private var books: List<BookData>) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
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
        val book: BookData = books[position]
        val tvTitle : TextView = holder.itemView.findViewById(R.id.tvTitle)
        val tvAuthor : TextView = holder.itemView.findViewById(R.id.tvAuthor)
        val tvStatus : TextView = holder.itemView.findViewById(R.id.tvStatus)
        tvTitle.text = book.title
        tvAuthor.text = book.author
        tvStatus.text = book.status
        val ibBookDetails : ImageButton = holder.itemView.findViewById(R.id.ibBookDetails)
        ibBookDetails.setOnClickListener {

        }
    }

    fun updateDataSet(books: List<BookData>?) {
        if (books != null) {
            this.books = books
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}