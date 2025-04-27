package com.example.mybooks.database.repository

import androidx.lifecycle.LiveData
import com.example.mybooks.database.dao.QuoteDao
import com.example.mybooks.database.model.QuoteData

class QuoteRepository(private val quoteDao: QuoteDao) {
    fun getAllQuotes(): LiveData<List<QuoteData>> = quoteDao.getAll()

    fun getQuoteById(id: Long): QuoteData = quoteDao.getById(id)

    fun getQuoteByBookId(id: Long): LiveData<List<QuoteData>> = quoteDao.getQuoteByBookId(id)

    fun addQuote(quote: QuoteData){
        quoteDao.insert(quote)
    }

    fun updateQuote(quote: QuoteData){
        quoteDao.update(quote)
    }

    fun deleteQuote(quote: QuoteData) {
        quoteDao.delete(quote)
    }

    fun setIsFavorite(id: Long, favorite: Boolean) {
        quoteDao.clearFavorite()
        quoteDao.setIsFavorite(id, favorite)
    }

    fun getFavoriteQuote(bookId: Long): String {
        return quoteDao.getFavoriteQuote(bookId)
    }
}