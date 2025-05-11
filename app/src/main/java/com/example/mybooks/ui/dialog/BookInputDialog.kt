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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookInputDialog(context: Context,
                      private val isEdit: Boolean,
                      private val bookData: BookData?,
                      private var addBookDialogListener: BookDialogListener
) : Dialog(context, R.style.DialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.book_dialog)

        var statusValue= ""
        var isFavorite = false
        var createdDateValue = ""
        var statusChangedDateValue = ""
        val currentStatusValue = bookData?.status

        // Initialize views
        val btnAdd: Button = findViewById(R.id.btnAddEditBook)
        val btnClose: ImageButton = findViewById(R.id.ibClose)
        val title: TextInputLayout = findViewById(R.id.tiTitle)
        val author: TextInputLayout = findViewById(R.id.tiAuthor)
        val status: Spinner = findViewById(R.id.spStatus)
        val rating: TextInputLayout = findViewById(R.id.tiRating)
        val favChar: TextInputLayout = findViewById(R.id.tiFavChar)
        val cost: TextInputLayout = findViewById(R.id.tiCost)

        // Set up the spinner
        ArrayAdapter.createFromResource(
            this.context,
            R.array.book_status,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
            status.adapter = adapter
        }

        // Set up the spinner listener
        status.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                statusValue = parent?.getItemAtPosition(position).toString()
                statusChangedDateValue = if (currentStatusValue != statusValue) {
                    SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.US
                    ).format(Calendar.getInstance().time)
                } else {
                    createdDateValue
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                statusValue = context.resources.getStringArray(R.array.book_status)[0]
            }
        }

        // Set up the close button
        btnClose.setOnClickListener{
            dismiss()
        }

        // Set up Edit Dialog
        if (isEdit) {
            // Initialize views with book data
            btnAdd.text = context.resources.getString(R.string.edit_button)
            title.editText?.setText(bookData?.title)
            author.editText?.setText(bookData?.author)

            // Set up the spinner for Edit
            val typeOptions = context.resources.getStringArray(R.array.book_status)
            when(val typeIndex = typeOptions.indexOf(currentStatusValue)) {
                -1 -> {
                    status.setSelection(0)
                }
                else -> {
                    status.setSelection(typeIndex)
                }
            }
            // Set up the rating, favorite character, and cost
            rating.editText?.setText(bookData?.rating.toString())
            favChar.editText?.setText(bookData?.favChar)
            cost.editText?.setText(bookData?.cost.toString())
            isFavorite = bookData!!.isFavorite
            createdDateValue = bookData.createdDate

        } else {
            createdDateValue = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Calendar.getInstance().time)
            statusChangedDateValue = createdDateValue
        }

        // Set up Add/Edit Button
        btnAdd.setOnClickListener{
            // Get the values from the input fields

            val titleValue = title.editText?.text.toString()
            val authorValue = author.editText?.text.toString()
            var ratingValue = rating.editText?.text.toString()
            var favCharValue = favChar.editText?.text.toString()
            var costValue = cost.editText?.text.toString()

            // Perform input validation
            if (titleValue.isEmpty() || authorValue.isEmpty()) {
                Toast.makeText(context, "Please enter title and author", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (ratingValue.isEmpty()) {
                ratingValue = 1.toString()
            } else if (ratingValue.toIntOrNull() == null || ratingValue.toInt() < 1 || ratingValue.toInt() > 10 ) {
                Toast.makeText(context, "Please enter a rating between 1 and 10", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (costValue.isEmpty()) {
                costValue = 0.0.toString()
            }
            else if (costValue.toFloatOrNull() == null || costValue.toFloat() < 0) {
                Toast.makeText(context, "Please enter valid cost", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (favCharValue.isEmpty()) {
                favCharValue = "-"
            }

            // Create a new BookData object and pass it to the listener
            val data = BookData(bookData?.id ?: 0, titleValue, authorValue, statusValue, createdDateValue, statusChangedDateValue, ratingValue.toInt(), favCharValue, costValue.toFloat(), isFavorite)
            addBookDialogListener.onAddButtonClicked(data)
            dismiss()
        }

    }
}