package com.example.mybooks.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import com.example.mybooks.R
import com.example.mybooks.database.model.QuoteData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class QuoteInputDialog(context: Context,
                       private val isEdit: Boolean,
                       private val quoteData: QuoteData?,
                       private val bookId: Long,
                       private var addQuoteDialogListener: QuoteDialogListener
) : Dialog(context, R.style.DialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.quote_dialog)

        val btnAdd: Button = findViewById(R.id.btnAddEdit)
        val enteredQuote: TextInputLayout = findViewById(R.id.tiQuote)
        val btnClose: ImageButton = findViewById(R.id.ibClose)

        if (isEdit) {
            btnAdd.text = context.resources.getString(R.string.edit_button)
            enteredQuote.editText?.setText(quoteData?.content)
        }

        btnClose.setOnClickListener {
            dismiss()
        }

        btnAdd.setOnClickListener {
            val quoteValue = enteredQuote.editText?.text.toString()
            val data = QuoteData(quoteData?.id ?: 0, quoteValue, false, bookId)
            addQuoteDialogListener.onAddButtonClicked(data)
            dismiss()
        }
    }
}