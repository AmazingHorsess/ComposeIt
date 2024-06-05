package com.composeit.task.mapper

import com.composeit.task.model.TaskWithCategory as ViewTaskWithCategory
import com.composeit.domain.model.TaskWithCategory as DomainTaskWithCategory

internal class TaskWithCategoryMapper(
    private val taskMapper: TaskMapper,
    private val categoryMapper: CategoryMapper,
) {
    /**
     * Maps Task With Category from Domain to View.
     *
     * @param localTaskList the list of Task With Category to be converted.
     *
     * @return the converted list of Task With Category
     */
    fun toView(localTaskList: List<DomainTaskWithCategory>): List<ViewTaskWithCategory> =
        localTaskList.map { toView(it) }.toList()

    private fun toView(localTask: DomainTaskWithCategory): ViewTaskWithCategory =
        ViewTaskWithCategory(
            task = taskMapper.toView(localTask.task),
            category = localTask.category?.let { categoryMapper.toView(it) },
        )
}