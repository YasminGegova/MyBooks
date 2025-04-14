package com.example.mybooks.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R

class QuotesAdapter(private val quotes: List<String>) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val quoteView = inflater.inflate(R.layout.quote_entry, parent, false)
        return ViewHolder(quoteView)
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote: String = quotes[position]
        val textView : TextView = holder.itemView.findViewById(R.id.tvQuote)
        textView.text = quote
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}