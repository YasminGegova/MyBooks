package com.example.mybooks.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Book")
data class BookData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val author: String,
    val status: String,
    val createdDate: String,
    val statusChangedDate: String,
    val rating: Int,
    val favChar: String,
    val cost: Float,
    val isFavorite: Boolean)