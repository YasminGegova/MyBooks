package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.database.model.BookData
import com.example.mybooks.database.repository.BookRepository
import com.example.mybooks.database.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookDetailsViewModel(application: Application): AndroidViewModel(application) {
    private val bookRepository: BookRepository
    private val quoteRepository: QuoteRepository
    lateinit var book: LiveData<BookData>
    private val _favoriteQuote = MutableLiveData<String>()
    val favoriteQuote: LiveData<String>
        get() = _favoriteQuote

    init {
        val bookDao = AppDatabase.getDatabase(application).bookDao()
        val quoteDao = AppDatabase.getDatabase(application).quoteDao()
        bookRepository = BookRepository(bookDao)
        quoteRepository = QuoteRepository(quoteDao)
    }

    fun getBookById(id: Long) {
        book = bookRepository.getBookById(id)
    }

    fun setIsFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.setIsFavorite(id, isFavorite)
        }
    }

    fun deleteBook(book: BookData) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(book)
        }
    }

    fun getFavoriteQuote(bookId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteQuote.postValue(quoteRepository.getFavoriteQuote(bookId))
        }
    }


}