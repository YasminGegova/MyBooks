package com.example.mybooks.viewmodel

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.R
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.database.model.BookData
import com.example.mybooks.database.repository.BookRepository
import com.example.mybooks.ui.BooksActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksViewModel(application: Application, resources: Resources, activityType: String): AndroidViewModel(application) {
    private val bookRepository: BookRepository
    var books: LiveData<List<BookData>>

    init {
        val bookDao = AppDatabase.getDatabase(application).bookDao()
        bookRepository = BookRepository(bookDao)
        books = bookRepository.getAllBooks()
        books = when (activityType) {
            resources.getString(R.string.favorites) -> {
                bookRepository.getFavorites()
            }
            resources.getString(R.string.wish_list) -> {
                bookRepository.getWishList()
            }
            else -> {
                bookRepository.getAllBooks()
            }
        }

    }

    fun addBook(bookData: BookData){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.addBook(bookData)
        }
    }

    fun updateBook(bookData: BookData){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.updateBook(bookData)
        }
    }
}