package com.example.mybooks

import java.util.Date

data class BookData(val title: String,
               val author: String,
               val status: String,
               val startDate: Date,
               val endDate: Date,
               val rating: Int,
               val favChar: String,
               val cost: Float) {

}