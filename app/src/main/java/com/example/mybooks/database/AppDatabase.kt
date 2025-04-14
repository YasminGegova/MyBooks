package com.example.mybooks.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mybooks.database.dao.BookDao
import com.example.mybooks.database.dao.QuoteDao
import com.example.mybooks.database.model.BookData
import com.example.mybooks.database.model.QuoteData

@Database(entities = [BookData::class, QuoteData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun quoteDao(): QuoteDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "BookDB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}