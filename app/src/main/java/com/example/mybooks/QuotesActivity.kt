package com.example.mybooks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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