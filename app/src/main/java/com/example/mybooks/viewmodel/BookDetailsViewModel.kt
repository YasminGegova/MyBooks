package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.database.model.BookData
import com.example.mybooks.database.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val bookRepository: BookRepository
    var book: BookData? = null

    init {
        val bookDao = AppDatabase.getDatabase(application).bookDao()
        bookRepository = BookRepository(bookDao)
    }

    fun getBookById(id: Int) {
        book = bookRepository.getBookById(id)
    }

    fun setIsFavorite(isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            book?.let {
                bookRepository.setIsFavorite(it.id.toInt(), isFavorite)
            }
        }
    }

    fun deleteBook(book: BookData){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }
}