package com.libraries.core.extension

import java.text.DateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.format(): String {
    val dateFormat = DateFormat.getDateTimeInstance(
        DateFormat.LONG,
        DateFormat.SHORT,
        Locale.getDefault(),
    )
    return dateFormat.format(time)
}