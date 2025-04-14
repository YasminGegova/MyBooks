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
    fun getById(id: Int): QuoteData

    @Insert
    fun insert(quote: QuoteData)

    @Update
    fun update(quote: QuoteData)

    @Delete
    fun delete(quote: QuoteData)

    @Query("UPDATE Quote SET isFavorite = 0 WHERE isFavorite = 1")
    fun clearFavorite()

    @Query("UPDATE Quote SET isFavorite = :favorite WHERE id = :id")
    fun setIsFavorite(id: Int, favorite: Boolean)
}