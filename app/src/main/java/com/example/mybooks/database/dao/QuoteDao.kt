package com.example.mybooks.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mybooks.database.model.QuoteData

@Dao
interface QuoteDao {

    @Query("SELECT * FROM Quote")
    fun getAll(): LiveData<List<QuoteData>>

    @Query("SELECT * FROM Quote WHERE id = :id")
    fun getById(id: Long): QuoteData

    @Query("SELECT * FROM Quote WHERE bookId = :id")
    fun getQuoteByBookId(id: Long): LiveData<List<QuoteData>>

    @Insert
    fun insert(quote: QuoteData)

    @Update
    fun update(quote: QuoteData)

    @Delete
    fun delete(quote: QuoteData)

    @Query("UPDATE Quote SET isFavorite = 0 WHERE isFavorite = 1")
    fun clearFavorite()

    @Query("UPDATE Quote SET isFavorite = :favorite WHERE id = :id")
    fun setIsFavorite(id: Long, favorite: Boolean)

    @Query("SELECT content FROM Quote WHERE bookId = :bookId AND isFavorite = 1")
    fun getFavoriteQuote(bookId: Long): String
}