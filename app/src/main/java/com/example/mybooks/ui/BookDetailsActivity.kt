package com.example.mybooks.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.R
import com.example.mybooks.database.model.BookData
import com.example.mybooks.ui.adapter.BooksAdapter
import com.example.mybooks.viewmodel.BookDetailsViewModel
import com.example.mybooks.viewmodel.BooksViewModel

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var book: BookData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Initialize the ViewModel
        val bookDetailsViewModel = ViewModelProvider(this)[BookDetailsViewModel::class.java]

        // Initialize the views
        val tvTitle: TextView = findViewById(R.id.tvTitle)
        val tvAuthor: TextView = findViewById(R.id.tvAuthor)
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        val tvStartDate: TextView = findViewById(R.id.tvStartDate)
        val tvEndDate: TextView = findViewById(R.id.tvEndDate)
        val tvRating: TextView = findViewById(R.id.tvRating)
        val tvFavChar: TextView = findViewById(R.id.tvFavChar)
        val tvCost: TextView = findViewById(R.id.tvCost)

        // Get the ID from the intent
        val intent = intent
        val extras = intent.extras
        val id = extras?.getLong("ID")

        // Get the book from the database
        bookDetailsViewModel.getBookById(id!!)

        // Observe the LiveData from the ViewModel
        bookDetailsViewModel.book.observe(this) {
            tvTitle.text = it.title
            tvAuthor.text = it.author
            tvStatus.text = it.status
            tvStartDate.text = it.startDate
            tvEndDate.text = it.endDate
            tvRating.text = it.rating.toString()
            tvFavChar.text = it.favChar
            tvCost.text = it.cost.toString()
        }

        // Configure click listener for the "Back" button
        val ibBack: ImageButton = findViewById(R.id.ibBack)
        ibBack.setOnClickListener {
            finish()
        }

        // Configure click listener for the "Quotes" button
        val btnQuotes: Button = findViewById(R.id.btnQuotes)
        btnQuotes.setOnClickListener {
            val intent = Intent(this, QuotesActivity::class.java)
            startActivity(intent)
        }
    }
}