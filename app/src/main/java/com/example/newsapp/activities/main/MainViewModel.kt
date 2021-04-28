package com.example.newsapp.activities.main

import com.example.newsapp.fragments.base.BaseViewModel
import java.util.*

class MainViewModel : BaseViewModel() {
    fun getFormattedDateString(year: Int, month: Int, day: Int): String {
        val monthString = if (month.toString().length == 1) "0${month}" else month.toString()
        val dayString = if (day.toString().length == 1) "0$day" else day.toString()
        return "$year-$monthString-$dayString"
    }

    fun getTenDaysBefore(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -10)
        return calendar
    }
}