package com.example.mybooks.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.database.model.BookData
import com.example.mybooks.ui.adapter.BooksAdapter
import com.example.mybooks.ui.decorator.BookItemDecorator
import com.example.mybooks.ui.dialog.BookDialogListener
import com.example.mybooks.ui.dialog.BookInputDialog
import com.example.mybooks.viewmodel.BooksViewModel


class BooksActivity : AppCompatActivity() {

    private lateinit var books: List<BookData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val bookVieModel = ViewModelProvider(this)[BooksViewModel::class.java]

        val recyclerView: RecyclerView = findViewById(R.id.rvAllBooks)
        books = emptyList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = BooksAdapter(books)

        bookVieModel.books.observe(this) {
            (recyclerView.adapter as BooksAdapter).updateDataSet(it)
        }

        val btnAddBook = findViewById<Button>(R.id.btnAddBook)
        btnAddBook.setOnClickListener {
            BookInputDialog(this,
                object : BookDialogListener {
                override fun onAddButtonClicked(bookData: BookData) {
                    bookVieModel.addBook(bookData)
                }
            }).show()
        }
    }
}