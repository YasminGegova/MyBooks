package com.example.mybooks.ui

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooks.R
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val prefLanguageKey = "language"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var activityType : String

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val storedLanguage = getLanguage()
        updateLocale(storedLanguage)
        if (savedInstanceState == null) {
            recreate()
        }

        val sLanguage : Switch = findViewById(R.id.sLanguage)
        sLanguage.isChecked = storedLanguage == "bg"

        sLanguage.setOnClickListener {
            val language = if (sLanguage.isChecked) "bg" else "en"
            setLanguage(language)
            updateLocale(language)
            recreate()
        }

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

    private fun setLanguage(language: String) {
        val editor = sharedPreferences.edit()
        editor.putString(prefLanguageKey, language)
        editor.apply()
    }

    private fun getLanguage(): String {
        return sharedPreferences.getString(prefLanguageKey, null) ?: "en"
    }

    private fun updateLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = this.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

}