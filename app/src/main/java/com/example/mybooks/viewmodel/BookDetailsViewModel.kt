package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.database.model.BookData
import com.example.mybooks.database.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val bookRepository: BookRepository
    lateinit var book: LiveData<BookData>

    init {
        val bookDao = AppDatabase.getDatabase(application).bookDao()
        bookRepository = BookRepository(bookDao)
    }

    fun getBookById(id: Long) {
        book = bookRepository.getBookById(id)
    }

    fun setIsFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.setIsFavorite(id, isFavorite)
        }
    }

    fun deleteBook(book: BookData){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }
}