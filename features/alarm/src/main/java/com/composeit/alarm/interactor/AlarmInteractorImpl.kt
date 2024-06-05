package com.composeit.alarm.interactor

import android.app.AlarmManager
import com.composeit.alarm.notification.TaskNotification
import com.composeit.alarm.notification.TaskNotificationScheduler
import com.composeit.domain.interactor.AlarmInteractor

internal class AlarmInteractorImpl(
    private val alarmManager: TaskNotificationScheduler
): AlarmInteractor {

    override fun schedule(alarmId: Long, timeMillis: Long) {
        alarmManager.scheduleTaskAlarm(alarmId,timeMillis)
    }

    override fun cancel(alarmId: Long) {
        alarmManager.cancelTaskAlarm(alarmId)
    }
}