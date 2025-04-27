package com.example.mybooks.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooks.R
import com.example.mybooks.database.model.QuoteData
import com.example.mybooks.ui.QuotesActivity
import com.example.mybooks.ui.dialog.QuoteDialogListener
import com.example.mybooks.ui.dialog.QuoteInputDialog


class QuotesAdapter(private var quotes: List<QuoteData>, private val activity: QuotesActivity) : RecyclerView.Adapter<QuotesAdapter.ViewHolder>()  {
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
        val quote: QuoteData = quotes[position]
        val textView : TextView = holder.itemView.findViewById(R.id.tvQuote)
        val tbFavorite : ToggleButton = holder.itemView.findViewById(R.id.tbAddToFav)
        val ibRemove : ImageButton = holder.itemView.findViewById(R.id.ibRemove)

        textView.text = quote.content

        holder.itemView.setOnClickListener {
            QuoteInputDialog(holder.itemView.context,
                true,
                quote,
                quote.id,
                object : QuoteDialogListener {
                    override fun onAddButtonClicked(quoteData: QuoteData) {
                       activity.onUpdateButtonClicked(quoteData)
                    }
                }).show()
        }

        tbFavorite.isChecked = quote.isFavorite
        tbFavorite.setOnClickListener {
            if (tbFavorite.isChecked) {
                activity.onFavoriteButtonClicked(quote.id, true)
            } else {
                activity.onFavoriteButtonClicked(quote.id, false)
            }
        }

        ibRemove.setOnClickListener {
            AlertDialog.Builder(this.activity)
                .setTitle("Delete quote")
                .setMessage("Are you sure you want to delete this quote?")

                .setPositiveButton(android.R.string.ok
                ) { _: DialogInterface, _: Int ->
                    activity.onRemoveButtonClicked(quote)
                } // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.cancel, null)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .show()
        }

    }

    fun updateDataSet(quotes: List<QuoteData>?) {
        if (quotes != null) {
            this.quotes = quotes
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}