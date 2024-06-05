package com.composeit.domain.usecase.alarm

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.model.AlarmInterval
import com.composeit.domain.model.Task
import com.composeit.domain.provider.CalendarProvider
import com.composeit.domain.repository.TaskRepository
import java.util.Calendar

class ScheduleNextAlarm(
    private val taskRepository: TaskRepository,
    private val alarmInteractor: AlarmInteractor,
    private val calendarProvider: CalendarProvider
) {
    suspend operator fun invoke(task: Task){
        require(task.isRepeating){" Task is not repeating"}
        require(task.dueDate != null){"Task has no due date"}
        require(task.alarmInterval != null){"Task has no interval"}
        val currentTime = calendarProvider.getCurrentCalendar()

        do {
            updateAlarmTime(task.dueDate,task.alarmInterval)
        } while (currentTime.after(task.dueDate))

        taskRepository.updateTask(task)
        alarmInteractor.schedule(task.id,task.dueDate.time.time)


    }
    private fun updateAlarmTime(calendar: Calendar, alarmInterval: AlarmInterval) =
        when(alarmInterval){
            AlarmInterval.HOURLY -> calendar.apply { add(Calendar.HOUR, 1) }
            AlarmInterval.DAILY -> calendar.apply { add(Calendar.DAY_OF_MONTH,1) }
            AlarmInterval.WEEKLY -> calendar.apply { add(Calendar.DAY_OF_WEEK,1) }
            AlarmInterval.MONTHLY -> calendar.apply { add(Calendar.MONTH, 1) }
            AlarmInterval.YEARLY -> calendar.apply { add(Calendar.YEAR,1) }
        }


}