package com.example.mybooks.database.repository

import androidx.lifecycle.LiveData
import com.example.mybooks.database.dao.BookDao
import com.example.mybooks.database.model.BookData

class BookRepository(private val bookDao: BookDao) {
    fun getAllBooks(): LiveData<List<BookData>> = bookDao.getAll()

    fun getBookById(id: Long): LiveData<BookData> = bookDao.getById(id)

    fun addBook(book: BookData){
        bookDao.insert(book)
    }

    fun updateBook(book: BookData){
        bookDao.update(book)
    }

    fun deleteBook(book: BookData){
        bookDao.delete(book)
    }

    fun setIsFavorite(id: Int, isFavorite: Boolean){
        val book = bookDao.setIsFavorite(id, isFavorite)
    }

}