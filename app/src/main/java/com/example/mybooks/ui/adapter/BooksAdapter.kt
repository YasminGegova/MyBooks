package com.example.mybooks.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.database.model.BookData
import com.example.mybooks.ui.BookDetailsActivity
import com.example.mybooks.ui.BooksActivity
import com.example.mybooks.ui.dialog.BookDialogListener
import com.example.mybooks.ui.dialog.BookInputDialog

class BooksAdapter(private var books: List<BookData>, private val activity: BooksActivity) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val bookView = inflater.inflate(R.layout.book_entry, parent, false)
        return ViewHolder(bookView)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val book: BookData = books[position]
        val tvTitle : TextView = holder.itemView.findViewById(R.id.tvTitle)
        val tvAuthor : TextView = holder.itemView.findViewById(R.id.tvAuthor)
        val tvStatus : TextView = holder.itemView.findViewById(R.id.tvStatus)

        // Set item views based on the views and data model
        tvTitle.text = book.title
        tvAuthor.text = book.author
        tvStatus.text = book.status

        // Configure click listener for the "Edit" button
        val ibEdit : ImageButton = holder.itemView.findViewById(R.id.ibEdit)
        ibEdit.setOnClickListener{
            // Create a new BookInputDialog and show it
            BookInputDialog(holder.itemView.context,
                true,
                book,
                object : BookDialogListener {
                    override fun onAddButtonClicked(bookData: BookData) {
                        activity.onUpdateButtonClicked(bookData)
                    }
                }).show()
        }

        // Configure click listener for the Book Details button
        val ibBookDetails : ImageButton = holder.itemView.findViewById(R.id.ibBookDetails)
        ibBookDetails.setOnClickListener {
            // Create an Intent to start the BookDetailsActivity and pass the ID
            val extras = Bundle()
            extras.putLong("ID", book.id)
            val intent = Intent(holder.itemView.context, BookDetailsActivity::class.java)
            intent.putExtras(extras)
            holder.itemView.context.startActivity(intent)
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