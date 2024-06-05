package com.composeit.local.converter

import androidx.room.TypeConverter
import java.util.Calendar

internal class DateConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long?): Calendar? {
        if (timestamp == null) {
            return null
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return calendar
    }

    @TypeConverter
    fun calendarToTimestamp(calendar: Calendar?): Long? {
        if (calendar == null) {
            return null
        }

        return calendar.timeInMillis
    }
}