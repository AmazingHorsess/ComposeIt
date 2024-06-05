package com.composeit.domain.usecase.alarm

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.interactor.NotificationInteractor
import com.composeit.domain.provider.CalendarProvider
import java.util.Calendar

class SnoozeAlarm (
    private val calendarProvider: CalendarProvider,
    private val notificationInteractor: NotificationInteractor,
    private val alarmInteractor: AlarmInteractor
){
    operator fun invoke(taskId:Long, minutes: Int = DEFAULT_SNOOZE){
        require(minutes > 0){"The delay minutes must be positive"}

        val snoozedTime = getSnoozedTask(calendarProvider.getCurrentCalendar(),minutes)
        alarmInteractor.schedule(taskId,snoozedTime)
        notificationInteractor.dismiss(taskId)

    }
    private fun getSnoozedTask(calendar: Calendar, minutes: Int): Long{
        val updatedCalendar = calendar.apply { add(Calendar.MINUTE,minutes) }
        return updatedCalendar.time.time
    }

    companion object{
        private const val DEFAULT_SNOOZE = 15
    }
}