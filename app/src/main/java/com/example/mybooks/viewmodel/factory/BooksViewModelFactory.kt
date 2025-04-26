package com.example.mybooks.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.viewmodel.BooksViewModel

class BooksViewModelFactory(
    private val application: Application,
    private val activityType: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(application, activityType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}