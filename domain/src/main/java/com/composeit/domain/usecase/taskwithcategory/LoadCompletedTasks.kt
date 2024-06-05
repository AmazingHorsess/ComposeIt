package com.composeit.domain.usecase.taskwithcategory

import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.TaskRepository
import com.composeit.domain.repository.TaskWithCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadCompletedTasks(
    private val repository: TaskWithCategoryRepository
) {

    operator fun invoke(): Flow<List<TaskWithCategory>> =
        repository.findAllTasksWithCategory()
            .map { list -> list.filter { item->
                item.task.completed

            } }
}