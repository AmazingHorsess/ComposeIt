package com.composeit.glance.mapper

import android.view.View
import com.composeit.domain.model.TaskWithCategory as DomainTask
import com.composeit.glance.model.Task as ViewTask

internal class TaskMapper {

    fun toView(localTaskList: List<DomainTask>): List<ViewTask> =
        localTaskList.map { toView(it) }

    private fun toView(taskWithCategory: DomainTask):ViewTask =
        ViewTask(
            id = taskWithCategory.task.id,
            title = taskWithCategory.task.title
        )

}