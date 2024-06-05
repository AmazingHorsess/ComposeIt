package com.composeit.domain.usecase.alarm.implementation

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.model.AlarmInterval
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.alarm.ScheduleAlarm
import com.composeit.domain.usecase.alarm.UpdateTaskAsRepeating
import java.util.Calendar

class UpdateTaskAsRepeatingImpl(
    private val taskRepository: TaskRepository,
): UpdateTaskAsRepeating {
    override suspend operator fun invoke(taskId: Long, interval: AlarmInterval?) {
        val task = taskRepository.findTaskById(taskId) ?: return

        val updatedTask = if (interval == null) {
            task.copy(alarmInterval = null, isRepeating = false)
        } else {
            task.copy(alarmInterval = interval, isRepeating = true)
        }

        taskRepository.updateTask(updatedTask)
    }
}