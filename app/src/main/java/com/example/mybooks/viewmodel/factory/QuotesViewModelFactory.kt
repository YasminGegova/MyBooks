package com.example.mybooks.viewmodel.factory

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.viewmodel.BooksViewModel
import com.example.mybooks.viewmodel.QuotesViewModel

class QuotesViewModelFactory(
    private val application: Application,
    private val bookId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return QuotesViewModel(application, bookId) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}