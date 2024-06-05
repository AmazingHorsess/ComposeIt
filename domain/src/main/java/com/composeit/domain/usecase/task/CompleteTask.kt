package com.composeit.domain.usecase.task

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.interactor.NotificationInteractor
import com.composeit.domain.model.Task
import com.composeit.domain.provider.CalendarProvider
import com.composeit.domain.repository.TaskRepository

class CompleteTask(
    private val taskRepository: TaskRepository,
    private val alarmInteractor: AlarmInteractor,
    private val notificationInteractor: NotificationInteractor,
    private val calendarProvider: CalendarProvider,
) {

    suspend operator fun invoke(taskId: Long) {
        val task = taskRepository.findTaskById(taskId) ?: return
        invoke(task)
    }

    suspend operator fun invoke(task: Task) {
        val updatedTask = updateTaskAsCompleted(task)
        taskRepository.updateTask(updatedTask)
        alarmInteractor.cancel(task.id)
        notificationInteractor.dismiss(task.id)
    }

    private fun updateTaskAsCompleted(task: Task) =
        task.copy(completed = true, completedDate = calendarProvider.getCurrentCalendar())


}