package com.example.mybooks

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

    private lateinit var books: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        val recyclerView: RecyclerView = findViewById(R.id.rvAllBooks)
        books = listOf("Book 1", "Book 2", "Book 3")
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = BooksAdapter(books)
        val ibBookDetails: Button = findViewById(R.id.btnAddBook)
        ibBookDetails.setOnClickListener {
            val intent = Intent(this, BookDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}