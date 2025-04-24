package com.example.mybooks.ui.dialog

import com.example.mybooks.database.model.BookData

interface BookDialogListener {

    fun onAddButtonClicked(bookData: BookData)

}