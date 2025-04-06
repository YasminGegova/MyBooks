package com.example.mybooks

import android.content.Context
import android.view.View
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BookInputDialog : DialogFragment() {

    fun MaterialAlertDialogBuilder.showDialog(
        context: Context,
        isDialogCancelable: Boolean = false
    ): androidx.appcompat.app.AlertDialog {
        val titleView = View.inflate(context, R.layout.book_dialog, null)
        setCustomTitle(titleView)
        setCancelable(isDialogCancelable)
        val alertDialog = create()
        alertDialog.setCanceledOnTouchOutside(isDialogCancelable)
        alertDialog.show()
        return alertDialog
    }
}