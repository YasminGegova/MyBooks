package com.example.mybooks.ui.dialog

import android.app.DatePickerDialog
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
import android.widget.TextView
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

        // Initialize views
        val btnAdd: Button = findViewById(R.id.btnAddEditBook)
        val btnClose: ImageButton = findViewById(R.id.ibClose)
        val title: TextInputLayout = findViewById(R.id.tiTitle)
        val author: TextInputLayout = findViewById(R.id.tiAuthor)
        val status: Spinner = findViewById(R.id.spStatus)
        val startDate: ImageButton = findViewById(R.id.ibStartDate)
        val endDate: ImageButton = findViewById(R.id.ibEndDate)
        val rating: TextInputLayout = findViewById(R.id.tiRating)
        val favChar: TextInputLayout = findViewById(R.id.tiFavChar)
        val cost: TextInputLayout = findViewById(R.id.tiCost)
        var tvStartDate: TextView = findViewById(R.id.tvStartDateInput)
        val tvEndDate: TextView = findViewById(R.id.tvEndDateInput)

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
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                statusValue = "New"
            }
        }

        var startDateValue = ""
        var endDateValue = ""

        // Set up the start date
        startDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    val myFormat = "dd/MM/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    startDateValue = sdf.format(calendar.time)
                    tvStartDate.text= startDateValue
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // Set up the end date
        endDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    val myFormat = "dd/MM/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    endDateValue = sdf.format(calendar.time)
                    tvEndDate.text = endDateValue
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
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
            val statusValue = bookData?.status
            when(val typeIndex = typeOptions.indexOf(statusValue)) {
                -1 -> status.setSelection(typeOptions.indexOf("New"))
                else -> status.setSelection(typeIndex)
            }
            // Set up the start and end dates
            startDateValue = bookData?.startDate.toString()
            if (startDateValue != "") {
                tvStartDate.text = startDateValue
            }
            endDateValue = bookData?.endDate.toString()
            if (endDateValue != "") {
                tvEndDate.text = endDateValue
            }
            // Set up the rating, favorite character, and cost
            rating.editText?.setText(bookData?.rating.toString())
            favChar.editText?.setText(bookData?.favChar)
            cost.editText?.setText(bookData?.cost.toString())
            isFavorite = bookData!!.isFavorite
        }

        // Set up Add/Edit Button
        btnAdd.setOnClickListener{
            // Get the values from the input fields
            val titleValue = title.editText?.text.toString()
            val authorValue = author.editText?.text.toString()
            val ratingValue = rating.editText?.text.toString()
            val favCharValue = favChar.editText?.text.toString()
            val costValue = cost.editText?.text.toString()

            // Perform input validation
            if (titleValue.isEmpty() || authorValue.isEmpty()) {
                Toast.makeText(context, "Please enter title and author", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a new BookData object and pass it to the listener
            val data = BookData(bookData?.id ?: 0, titleValue, authorValue, statusValue, startDateValue, endDateValue, ratingValue.toInt(), favCharValue, costValue.toFloat(), isFavorite, false)
            addBookDialogListener.onAddButtonClicked(data)
            dismiss()
        }

    }
}