package com.example.mybooks.ui.dialog

import com.example.mybooks.database.model.QuoteData

interface QuoteDialogListener {

    fun onAddButtonClicked(quoteData: QuoteData)
}