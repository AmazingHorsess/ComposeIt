package com.composeit.domain.usecase.task

import com.composeit.domain.interactor.AlarmInteractor
import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository

class DeleteTask(
    private val alarmInteractor: AlarmInteractor,
    private val taskRepository: TaskRepository,
) {

    suspend operator fun invoke(task:Task){
        taskRepository.deleteTask(task)
        alarmInteractor.cancel(task.id)
    }
}