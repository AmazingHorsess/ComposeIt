package com.composeit.domain.provider

import java.util.Calendar

class CalendarProviderImpl: CalendarProvider {

    override fun getCurrentCalendar(): Calendar = Calendar.getInstance()

}