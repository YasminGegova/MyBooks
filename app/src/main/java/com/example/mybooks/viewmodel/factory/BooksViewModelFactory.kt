package com.example.mybooks.viewmodel.factory

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mybooks.ui.BooksActivity
import com.example.mybooks.viewmodel.BooksViewModel

@Suppress("UNCHECKED_CAST")
class BooksViewModelFactory(
    private val application: Application,
    private val resources: Resources,
    private val activityType: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(application, resources, activityType) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}