package com.composeit.search.mapper

import androidx.compose.ui.graphics.Color
import com.composeit.domain.model.TaskWithCategory
import com.composeit.search.model.TaskSearchItem

internal class TaskSearchMapper {

    fun toView(domainTaskList: List<TaskWithCategory>): List<TaskSearchItem> =
        domainTaskList.map { toView(it) }

    private fun toView(domainTask: TaskWithCategory): TaskSearchItem =
        TaskSearchItem(
            id = domainTask.task.id,
            completed = domainTask.task.completed,
            title = domainTask.task.title,
            categoryColor = getColor(domainTask.category?.color),
            isRepeating = domainTask.task.isRepeating,
        )

    private fun getColor(color: String?): Color? {
        if (color == null) {
            return null
        }
        return Color(android.graphics.Color.parseColor(color))
    }
}