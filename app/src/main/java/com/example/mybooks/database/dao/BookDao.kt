package com.example.mybooks.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mybooks.database.model.BookData

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun getAll(): LiveData<List<BookData>>

    @Query("SELECT * FROM Book WHERE isFavorite = 1")
    fun getFavorites(): LiveData<List<BookData>>

    @Query("SELECT * FROM Book WHERE status = \"Wish list\"")
    fun getWishList(): LiveData<List<BookData>>

    @Query("SELECT * FROM Book WHERE id = :id")
    fun getById(id: Long): LiveData<BookData>

    @Insert
    fun insert(book: BookData)

    @Delete
    fun delete(book: BookData)

    @Update
    fun update(book: BookData)

    @Query("UPDATE Book SET isFavorite = :isFavorite WHERE id = :id")
    fun setIsFavorite(id: Long, isFavorite: Boolean)

}