package com.example.newsapp.activities.main

import org.junit.Test

import org.junit.Assert.*
import java.util.*

class MainViewModelTest {

    @Test
    fun getFormattedDateString() {
        val expected = "1995-07-21"
        val actual = MainViewModel().getFormattedDateString(1995, 7, 21)
        assertEquals(expected, actual)
    }

    @Test
    fun getTenDaysBefore() {
        val expectedCalendar = Calendar.getInstance()
        expectedCalendar.add(Calendar.DAY_OF_MONTH, -10)
        val expected = MainViewModel().getFormattedDateString(
            expectedCalendar.get(Calendar.YEAR),
            expectedCalendar.get(Calendar.MONTH),
            expectedCalendar.get(Calendar.DAY_OF_MONTH))
        
        val actualCalendar = MainViewModel().getTenDaysBefore()
        val actual = MainViewModel().getFormattedDateString(
            actualCalendar.get(Calendar.YEAR),
            actualCalendar.get(Calendar.MONTH),
            actualCalendar.get(
                Calendar.DAY_OF_MONTH
            )
        )
        assertEquals(expected, actual)
    }
}