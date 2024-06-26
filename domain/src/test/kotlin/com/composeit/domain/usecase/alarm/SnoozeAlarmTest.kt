package com.composeit.domain.usecase.alarm

import com.composeit.domain.model.Task
import com.composeit.domain.provider.CalendarProvider
import com.composeit.domain.usecase.fake.AlarmInteractorFake
import com.composeit.domain.usecase.fake.CalendarProviderFake
import com.composeit.domain.usecase.fake.NotificationInteractorFake
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Calendar

internal class SnoozeAlarmTest {

    private val calendarProvider = CalendarProviderFake()

    private val notificationInteractor = NotificationInteractorFake()

    private val alarmInteractor = AlarmInteractorFake()

    private val snoozeAlarmUseCase = SnoozeAlarm(calendarProvider,notificationInteractor,alarmInteractor)

    private val baseTask = Task(id = 2345L, title = "it's time")

    @Before
    fun setup() = runTest {
        alarmInteractor.clear()
        notificationInteractor.clear()
    }

    @Test
    fun `test if task is snoozed`() = runTest {
        val snoozeTime = 15

        snoozeAlarmUseCase(baseTask.id, snoozeTime)

        val calendarAssert = Calendar.getInstance().apply {
            time = calendarProvider.getCurrentCalendar().time
            add(Calendar.MINUTE, snoozeTime)
        }
        val result = alarmInteractor.getAlarmTime(baseTask.id)
        Assert.assertEquals(calendarAssert.time.time, result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test if error is shown when snoozing with negative number`() = runTest {
        snoozeAlarmUseCase(baseTask.id, -15)
    }

    @Test
    fun `test if notification is dismissed`() {
        notificationInteractor.show(baseTask)

        snoozeAlarmUseCase(baseTask.id, 15)

        notificationInteractor.isNotificationShown(baseTask.id)
    }
}