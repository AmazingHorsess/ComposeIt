package com.composeit.domain.usecase.alarm.implementation

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.alarm.CancelAlarm

internal class CancelAlarmImpl(
    private val alarmInteractor: AlarmInteractor,
    private val taskRepository: TaskRepository
): CancelAlarm {
    override suspend fun invoke(taskId: Long) {
        val task = taskRepository.findTaskById(taskId) ?: return

        val updatedTask = task.copy(dueDate = null)
        alarmInteractor.cancel(task.id)
        taskRepository.updateTask(updatedTask)

    }
}