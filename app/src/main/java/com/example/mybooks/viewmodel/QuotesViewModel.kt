package com.example.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mybooks.database.AppDatabase
import com.example.mybooks.database.model.QuoteData
import com.example.mybooks.database.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesViewModel(application: Application, bookId: Long): AndroidViewModel(application) {
    private val quoteRepository: QuoteRepository
    var quotes: LiveData<List<QuoteData>>

    init {
        val quoteDao = AppDatabase.getDatabase(application).quoteDao()
        quoteRepository = QuoteRepository(quoteDao)
        quotes = quoteRepository.getQuoteByBookId(bookId)
    }

    fun addQuote(quoteData: QuoteData){
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.addQuote(quoteData)
        }
    }

    fun deleteQuote(quoteData: QuoteData){
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.deleteQuote(quoteData)
        }
    }

    fun setIsFavorite(id: Long, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.setIsFavorite(id, isFavorite)
        }
    }

    fun updateQuote(quoteData: QuoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.updateQuote(quoteData)
        }
    }
}