package com.composeit.domain.usecase.task.implementatiom

import android.util.Log
import com.composeit.domain.interactor.GlanceInteractor
import com.composeit.domain.model.Task
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.usecase.task.AddTask

internal class AddTaskImpl(
    private val taskRepository: TaskRepository,
    private val glanceInteractor: GlanceInteractor
): AddTask {
    override suspend operator fun invoke(task: Task){
        if (task.title.isBlank()){
            return
        }
        taskRepository.insertTask(task)
        glanceInteractor.onTaskListUpdated()
    }

}