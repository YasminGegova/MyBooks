package com.example.mybooks

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BooksActivity : AppCompatActivity() {

    private lateinit var books: List<BookData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val recyclerView: RecyclerView = findViewById(R.id.rvAllBooks)
        books = emptyList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = BooksAdapter(books)

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.book_dialog)

        val btnAddBook: Button = findViewById(R.id.btnAddBook)
        btnAddBook.setOnClickListener {
            dialog.show()
        }

        val btnClose: ImageButton = dialog.findViewById(R.id.ibClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

    }
}