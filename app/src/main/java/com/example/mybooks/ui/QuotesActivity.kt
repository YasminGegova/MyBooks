package com.example.mybooks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.ui.decorator.BookItemDecorator
import com.example.mybooks.ui.adapter.QuotesAdapter
import com.example.mybooks.R

class QuotesActivity : AppCompatActivity() {

    private lateinit var quotes: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        val recyclerView: RecyclerView = findViewById(R.id.rvAllQuotes)
        quotes = listOf("Quote1", "Quote2", "Quote3")
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bookItemDecorator = BookItemDecorator(32)
        recyclerView.addItemDecoration(bookItemDecorator)
        recyclerView.adapter = QuotesAdapter(quotes)
    }
}