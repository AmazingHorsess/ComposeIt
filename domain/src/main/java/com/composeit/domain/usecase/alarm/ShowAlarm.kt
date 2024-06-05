package com.composeit.domain.usecase.alarm

import com.composeit.domain.interactor.NotificationInteractor
import com.composeit.domain.repository.TaskRepository

class ShowAlarm(
    private val taskRepository: TaskRepository,
    private val notificationInteractor: NotificationInteractor,
    private val scheduleNextAlarm: ScheduleNextAlarm
) {
    suspend operator fun invoke(taskId: Long){
        val task = taskRepository.findTaskById(taskId)?: return

        if (task.completed){
            return
        } else{
            notificationInteractor.show(task)
        }

        if (task.isRepeating){
            scheduleNextAlarm(task)
        }
    }
}