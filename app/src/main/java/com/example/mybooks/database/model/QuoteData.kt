package com.example.mybooks.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BookData::class,
            parentColumns = ["id"],
            childColumns = ["bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = "Quote")
data class QuoteData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val content: String,
    val isFavorite: Boolean,
    val bookId: Long

) {
}