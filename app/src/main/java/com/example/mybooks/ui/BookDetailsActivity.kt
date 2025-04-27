package com.example.mybooks.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.R
import com.example.mybooks.viewmodel.BookDetailsViewModel

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var bookDetailsViewModel: BookDetailsViewModel
    private var bookId: Long? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        // Initialize the ViewModel
        bookDetailsViewModel = ViewModelProvider(this)[BookDetailsViewModel::class.java]

        // Initialize the views
        val tvTitle: TextView = findViewById(R.id.tvTitle)
        val tvAuthor: TextView = findViewById(R.id.tvAuthor)
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        val tvStartDate: TextView = findViewById(R.id.tvStartDate)
        val tvEndDate: TextView = findViewById(R.id.tvEndDate)
        val tvRating: TextView = findViewById(R.id.tvRating)
        val tvFavChar: TextView = findViewById(R.id.tvFavChar)
        val tvCost: TextView = findViewById(R.id.tvCost)
        val tbFavorite: ToggleButton = findViewById(R.id.tbFavorite)
        var tvFavQuote: TextView = findViewById(R.id.tvFavQuote)

        // Get the ID from the intent
        val intent = intent
        val extras = intent.extras
        bookId = extras?.getLong("ID")

        tbFavorite.setOnClickListener {
            if (tbFavorite.isChecked) {
                bookDetailsViewModel.setIsFavorite(bookId!!, true)
            } else {
                bookDetailsViewModel.setIsFavorite(bookId!!, false)
            }
        }

        // Get the book from the database
        bookDetailsViewModel.getBookById(bookId!!)

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
            tbFavorite.isChecked = it.isFavorite
        }

        bookDetailsViewModel.favoriteQuote.observe(this) {
            tvFavQuote.text = it
        }

        // Configure click listener for the "Back" button
        val ibBack: ImageButton = findViewById(R.id.ibBack)
        ibBack.setOnClickListener {
            finish()
        }

        // Configure click listener for the "Quotes" button
        val btnQuotes: Button = findViewById(R.id.btnQuotes)
        btnQuotes.setOnClickListener {
            val intentQuotes = Intent(this, QuotesActivity::class.java)
            intentQuotes.putExtra("BOOK_ID", bookId)
            startActivity(intentQuotes)
        }

        // Configure click listener for the "Delete" button
        val ibDelete: Button = findViewById(R.id.btnDelete)
        ibDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete book")
                .setMessage("Are you sure you want to delete this book?")

                .setPositiveButton(android.R.string.ok
                ) { _: DialogInterface, _: Int ->
                    bookDetailsViewModel.book.removeObservers(this)
                    bookDetailsViewModel.deleteBook(bookDetailsViewModel.book.value!!)
                    finish()
                } // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.cancel, null)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        bookDetailsViewModel.getFavoriteQuote(bookId!!)
    }
}