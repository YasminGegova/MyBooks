package com.example.mybooks.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class BookData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val author: String,
    val status: String,
    val startDate: String = "",
    val endDate: String = "",
    val rating: Int = 0,
    val favChar: String = "",
    val cost: Float = 0.0f,
    val isFavorite: Boolean = false,
    val isInWishList: Boolean = false) {

}