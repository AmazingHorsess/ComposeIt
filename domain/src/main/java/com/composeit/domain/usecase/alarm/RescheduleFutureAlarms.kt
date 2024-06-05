package com.composeit.domain.usecase.alarm

import android.app.Application.OnProvideAssistDataListener
import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.model.Task
import com.composeit.domain.provider.CalendarProvider
import com.composeit.domain.repository.TaskRepository
import java.util.Calendar

class RescheduleFutureAlarms(
    private val taskRepository: TaskRepository,
    private val alarmInteractor: AlarmInteractor,
    private val calendarProvider: CalendarProvider,
    private val scheduleNextAlarm: ScheduleNextAlarm
) {

    suspend operator fun invoke(){
        val uncompletedAlarms = taskRepository.findAllTasksWithDueDate().filterNot { it.completed }

        val futureAlarms = uncompletedAlarms.filter { isInFuture(it.dueDate) }
        val missingRepeating = uncompletedAlarms.filter { isMissedRepeating(it) }
    }

    private fun isInFuture(calendar: Calendar?): Boolean{
        val currentTime = calendarProvider.getCurrentCalendar()
        return calendar?.after(currentTime) ?: false
    }

    private fun isMissedRepeating(task: Task): Boolean{
        val currentTime = calendarProvider.getCurrentCalendar()
        return task.isRepeating && task.dueDate?.before(currentTime) ?: false

    }
    private fun rescheduleFutureTask(task: Task){
        val futureTime = task.dueDate?.time?.time ?:return
        alarmInteractor.schedule(task.id,futureTime)
    }
    private suspend fun rescheduleRepeatingTask(task: Task){
        scheduleNextAlarm(task)
    }
}