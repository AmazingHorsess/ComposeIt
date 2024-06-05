package com.composeit.domain.usecase.fake

import com.composeit.domain.provider.CalendarProvider
import java.util.Calendar
import java.util.GregorianCalendar

internal class CalendarProviderFake: CalendarProvider {
    override fun getCurrentCalendar(): Calendar
    = GregorianCalendar(2005,3,15,16,50,0)


}