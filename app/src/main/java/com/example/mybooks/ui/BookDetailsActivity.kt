package com.example.mybooks.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooks.R

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        val btnQuotes: Button = findViewById(R.id.btnQuotes)

        btnQuotes.setOnClickListener {
            val intent = Intent(this, QuotesActivity::class.java)
            startActivity(intent)
        }
    }
}