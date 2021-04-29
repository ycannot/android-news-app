package com.example.newsapp.helpers

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class DatePickerFragmentTest {

    @Test
    fun getCalendarForSelectedDate1() {
        val calendar = Calendar.getInstance()
        val actual = DatePickerFragment(null, null).getCalendarForSelectedDate(calendar, null)
        assertEquals(calendar.time, actual.time)
    }
    @Test
    fun getCalendarForSelectedDate2() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 1995)
        calendar.set(Calendar.MONTH, 6)
        calendar.set(Calendar.DAY_OF_MONTH, 21)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val actual = DatePickerFragment(null, "1995-07-21").getCalendarForSelectedDate(calendar, null)
        assertEquals(calendar.time, actual.time)
    }
}