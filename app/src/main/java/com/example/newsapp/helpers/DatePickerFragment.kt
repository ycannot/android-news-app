package com.example.newsapp.helpers

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.newsapp.R
import java.util.*

class DatePickerFragment(
    val listener: DatePickerDialog.OnDateSetListener?,
    val date: String? = null
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val selectedDateCalendar = getCalendarForSelectedDate(Calendar.getInstance(), date)
        val year = selectedDateCalendar.get(Calendar.YEAR)
        val month = selectedDateCalendar.get(Calendar.MONTH)
        val day = selectedDateCalendar.get(Calendar.DAY_OF_MONTH)
        // Create a new instance of DatePickerDialog
        val dialog = DatePickerDialog(requireActivity(), listener, year, month, day)
        dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        dialog.setMessage(getString(R.string.date_picker_title))
        return dialog
    }

    fun getCalendarForSelectedDate(calendar: Calendar, dateString: String?): Calendar{
        date?.let {
            val splitted:MutableList<String> = mutableListOf()
            splitted.addAll(it.split("-"))
            return GregorianCalendar(splitted[0].toInt(), splitted[1].toInt()-1, splitted[2].toInt())
        }
        return calendar
    }
}