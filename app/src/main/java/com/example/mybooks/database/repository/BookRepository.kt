package com.example.mybooks.database.repository

import androidx.lifecycle.LiveData
import com.example.mybooks.database.dao.BookDao
import com.example.mybooks.database.model.BookData

class BookRepository(private val bookDao: BookDao) {
    fun getAllBooks(): LiveData<List<BookData>> = bookDao.getAll()

    fun getFavorites(): LiveData<List<BookData>> = bookDao.getFavorites()

    fun getWishList(): LiveData<List<BookData>> = bookDao.getWishList()
    
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

    fun setIsFavorite(id: Long, isFavorite: Boolean){
        bookDao.setIsFavorite(id, isFavorite)
    }

}