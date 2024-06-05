package com.composeit.domain.usecase.alarm.implementation

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.alarm.ScheduleAlarm
import java.util.Calendar

internal class ScheduleAlarmImpl(
    private val taskRepository: TaskRepository,
    private val alarmInteractor: AlarmInteractor
): ScheduleAlarm {
    override suspend fun invoke(taskId: Long, calendar: Calendar) {
        val task = taskRepository.findTaskById(taskId) ?: return
        val updatedTask = task.copy(dueDate = calendar)
        taskRepository.updateTask(updatedTask)

        alarmInteractor.schedule(taskId, calendar.time.time)
    }
}