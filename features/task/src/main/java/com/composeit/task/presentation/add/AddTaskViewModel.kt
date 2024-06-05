package com.composeit.task.presentation.add

import androidx.lifecycle.ViewModel
import com.composeit.domain.model.Task
import com.composeit.domain.usecase.task.AddTask
import com.composeit.task.mapper.TaskMapper
import com.composeit.task.presentation.detail.main.CategoryId
import com.libraries.core.coroutines.AppCoroutineScope

internal class AddTaskViewModel(
    private val addTaskUseCase: AddTask,
    private val applicationScope: AppCoroutineScope,
    private val taskMapper: TaskMapper,
) : ViewModel() {

    fun addTask(title: String, categoryId: CategoryId?) {
        if (title.isBlank()) return

        applicationScope.launch {
            val task = Task(title = title, categoryId = categoryId?.value)
            addTaskUseCase.invoke(task)
        }
    }
}