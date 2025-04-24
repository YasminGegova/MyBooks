package com.example.mybooks.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
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

    private lateinit var bookViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        // Initialize the ViewModel
        bookViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        // Initialize the RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvAllBooks)
        books = emptyList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = BooksAdapter(books, this)

        // Observe the LiveData from the ViewModel
        bookViewModel.books.observe(this) {
            (recyclerView.adapter as BooksAdapter).updateDataSet(it)
        }

        // Configure click listener for the "Add Book" button
        val btnAddBook: Button = findViewById(R.id.btnAddBook)
        btnAddBook.setOnClickListener {
            BookInputDialog(this,
                false,
                null,
                object : BookDialogListener {
                override fun onAddButtonClicked(bookData: BookData) {
                    bookViewModel.addBook(bookData)
                }
            }).show()
        }

        // Configure click listener for the "Back" button
        val ibBack: ImageButton = findViewById(R.id.ibBack)
        ibBack.setOnClickListener {
            finish()
        }
    }

    fun onUpdateButtonClicked(bookData: BookData) {
        // Handle the update button click event
        bookViewModel.updateBook(bookData)
    }
}