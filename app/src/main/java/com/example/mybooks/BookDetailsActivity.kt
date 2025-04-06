package com.example.mybooks

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        val btnQuotes: Button = findViewById(R.id.btnQuotes)
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.book_dialog)
        btnQuotes.setOnClickListener {
//            val intent = Intent(this, QuotesActivity::class.java)
//            startActivity(intent)

            dialog.show()

        }
    }
}