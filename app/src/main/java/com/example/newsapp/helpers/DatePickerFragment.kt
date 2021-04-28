package com.example.newsapp.helpers

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.newsapp.R
import java.util.*

class DatePickerFragment(
    val listener: DatePickerDialog.OnDateSetListener,
    val date: String? = null
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        var c = Calendar.getInstance()
        val maxDate = c.timeInMillis
        date?.let {
            val splitted:MutableList<String> = mutableListOf()
            splitted.addAll(it.split("-"))
            c = GregorianCalendar(splitted[0].toInt(), splitted[1].toInt()-1, splitted[2].toInt())
        }
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        // Create a new instance of DatePickerDialog
        val dialog = DatePickerDialog(requireActivity(), listener, year, month, day)
        dialog.datePicker.maxDate = maxDate
        dialog.setMessage(getString(R.string.date_picker_title))
        // return it
        return dialog
    }
}