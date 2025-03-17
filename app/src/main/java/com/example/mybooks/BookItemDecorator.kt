package com.example.mybooks

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BookItemDecorator(private val marginSize: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = marginSize
            }
            left = marginSize
            right = marginSize
            bottom = marginSize
        }
    }
}