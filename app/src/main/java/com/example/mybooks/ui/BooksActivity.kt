package com.example.mybooks.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
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
import com.example.mybooks.viewmodel.factory.BooksViewModelFactory


class BooksActivity : AppCompatActivity() {

    private lateinit var books: List<BookData>
    private lateinit var bookViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        // Get the ID from the intent
        val intent = intent
        val extras = intent.extras
        val activityType = extras?.getString("ACTIVITY_TYPE")

        val recyclerView: RecyclerView = findViewById(R.id.rvAllBooks)
        val tvActivityName: TextView = findViewById(R.id.tvAllBooks)
        val btnAddBook: Button = findViewById(R.id.btnAddBook)
        val ibBack: ImageButton = findViewById(R.id.ibBack)

        // Initialize the ViewModel
        bookViewModel = ViewModelProvider(this, BooksViewModelFactory(application, activityType!!))[BooksViewModel::class.java]

        if (activityType == resources.getString(R.string.favorites)) {
            tvActivityName.text = getString(R.string.favorites)
            btnAddBook.visibility = Button.INVISIBLE
        } else if (activityType == resources.getString(R.string.wish_list)) {
            btnAddBook.visibility = Button.INVISIBLE
            tvActivityName.text = getString(R.string.wish_list)
        }

        // Initialize the RecyclerView
        books = emptyList()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = BooksAdapter(books, this)

        var isBookAdded = false

        // Observe the LiveData from the ViewModel
        bookViewModel.books.observe(this) { books ->
            books.let {
                (recyclerView.adapter as BooksAdapter).updateDataSet(it)
                if (isBookAdded) {
                    recyclerView.scrollToPosition(books.lastIndex)
                    isBookAdded = false
                }
            }
        }

        // Configure click listener for the "Add Book" button
        btnAddBook.setOnClickListener {
            isBookAdded = true
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
        ibBack.setOnClickListener {
            finish()
        }
    }

    fun onUpdateButtonClicked(bookData: BookData) {
        // Handle the update button click event
        bookViewModel.updateBook(bookData)
    }
}