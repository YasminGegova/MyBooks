package com.example.mybooks.database.repository

import androidx.lifecycle.LiveData
import com.example.mybooks.database.dao.QuoteDao
import com.example.mybooks.database.model.QuoteData

class QuoteRepository(private val quoteDao: QuoteDao) {
    fun getAllQuotes(): LiveData<List<QuoteData>> = quoteDao.getAll()

    fun getQuoteById(id: Int): QuoteData = quoteDao.getById(id)

    fun addQuote(quote: QuoteData){
        quoteDao.insert(quote)
    }

    fun updateQuote(quote: QuoteData){
        quoteDao.update(quote)
    }

    fun deleteQuote(quote: QuoteData) {
        quoteDao.delete(quote)
    }

    fun setIsFavorite(id: Int, favorite: Boolean) {
        quoteDao.clearFavorite()
        quoteDao.setIsFavorite(id, favorite)
    }
}