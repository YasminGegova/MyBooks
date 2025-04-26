package com.example.mybooks.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooks.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var activityType : String

        // Configure click listeners for all books button
        val btnAllBooks : Button = findViewById(R.id.btnAllBooks)
        btnAllBooks.setOnClickListener {
            val intent = Intent(this, BooksActivity::class.java)
            activityType = "All_Books"
            intent.putExtra("ACTIVITY_TYPE", activityType)
            startActivity(intent)
        }

        val btnWishList : Button = findViewById(R.id.btnWishList)
        btnWishList.setOnClickListener {
            val intent = Intent(this, BooksActivity::class.java)
            activityType = "Wish List"
            intent.putExtra("ACTIVITY_TYPE", activityType)
            startActivity(intent)
        }

        val btnFavorites : Button = findViewById(R.id.btnFavorites)
        btnFavorites.setOnClickListener {
            val intent = Intent(this, BooksActivity::class.java)
            activityType = "Favorites"
            intent.putExtra("ACTIVITY_TYPE", activityType)
            startActivity(intent)
        }
    }
}