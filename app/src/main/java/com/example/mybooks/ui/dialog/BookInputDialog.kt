package com.example.mybooks.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import com.example.mybooks.R
import com.example.mybooks.database.model.BookData
import com.google.android.material.textfield.TextInputLayout

class BookInputDialog(context: Context, private var addBookDialogListener: BookDialogListener) : Dialog(context, R.style.DialogStyle) {
    private var statusValue= ""
    private lateinit var btnAdd: Button
    private lateinit var btnClose: ImageButton
    private lateinit var title: TextInputLayout
    private lateinit var author: TextInputLayout
    private lateinit var status: Spinner
    private lateinit var startDate: ImageButton
    private lateinit var endDate: ImageButton
    private lateinit var rating: TextInputLayout
    private lateinit var favChar: TextInputLayout
    private lateinit var cost: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.book_dialog)

        initViews()

        ArrayAdapter.createFromResource(
            this.context,
            R.array.book_status,
            R.layout.spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            // Apply the adapter to the spinner.
            status.adapter = adapter
        }

        status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle the selected item
                statusValue = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Use Personal in case of no selection
                statusValue = "New"
            }
        }

        btnClose.setOnClickListener{
            dismiss()
        }

        btnAdd.setOnClickListener{
            val titleValue = title.editText?.text.toString()
            val authorValue = author.editText?.text.toString()

            if (titleValue.isEmpty() && authorValue.isEmpty()) {
                Toast.makeText(context, "Please enter title and author", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = BookData(0, titleValue, authorValue, statusValue)
            addBookDialogListener.onAddButtonClicked(data)
            dismiss()
        }

    }

    private fun initViews() {
        btnAdd = findViewById(R.id.btnAddEditBook)
        btnClose = findViewById(R.id.ibCloseBookDialog)
        title = findViewById(R.id.tiTitle)
        author = findViewById(R.id.tiAuthor)
        status = findViewById(R.id.spStatus)
        startDate = findViewById(R.id.ibStartDate)
        endDate = findViewById(R.id.ibEndDate)
        rating = findViewById(R.id.tiRating)
        favChar = findViewById(R.id.tiFavChar)
        cost = findViewById(R.id.tiCost)

    }
}