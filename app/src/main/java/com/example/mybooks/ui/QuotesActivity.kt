package com.example.mybooks.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.database.model.QuoteData
import com.example.mybooks.ui.adapter.QuotesAdapter
import com.example.mybooks.ui.decorator.BookItemDecorator
import com.example.mybooks.ui.dialog.QuoteDialogListener
import com.example.mybooks.ui.dialog.QuoteInputDialog
import com.example.mybooks.viewmodel.QuotesViewModel
import com.example.mybooks.viewmodel.factory.QuotesViewModelFactory

class QuotesActivity : AppCompatActivity() {

    private lateinit var quotes: List<QuoteData>
    private lateinit var quoteViewModel: QuotesViewModel
    private var bookId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val recyclerView: RecyclerView = findViewById(R.id.rvAllQuotes)
        val btnAdd: Button = findViewById(R.id.btnAddQuote)
        val btnBack: ImageButton = findViewById(R.id.ibBack)

        val extras = intent.extras
        bookId = extras?.getLong("BOOK_ID")!!

        quoteViewModel = ViewModelProvider(this, QuotesViewModelFactory(application, bookId))[QuotesViewModel::class.java]

        quotes = emptyList()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = QuotesAdapter(quotes, this)

        quoteViewModel.quotes.observe(this) { quotes ->
            quotes.let {
                (recyclerView.adapter as QuotesAdapter).updateDataSet(it)
                recyclerView.scrollToPosition(quotes.lastIndex)
            }
        }

        btnAdd.setOnClickListener {
            QuoteInputDialog(this,
                false,
                null,
                bookId,
                object : QuoteDialogListener {
                    override fun onAddButtonClicked(quoteData: QuoteData) {
                        quoteViewModel.addQuote(quoteData)
                    }
                }).show()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    fun onUpdateButtonClicked(quoteData: QuoteData) {
        // Handle the update button click event
        quoteViewModel.updateQuote(quoteData)
    }

    fun onFavoriteButtonClicked(id: Long, isFavorite: Boolean) {
        quoteViewModel.setIsFavorite(id, isFavorite)
    }

    fun onRemoveButtonClicked(quoteData: QuoteData) {
        quoteViewModel.deleteQuote(quoteData)
    }
}