package com.composeit.alarm.mapper

import com.composeit.alarm.model.Task as ViewTask
import com.composeit.domain.model.Task as DomainTask

internal class TaskMapper {

    fun toView(domainTask: DomainTask): ViewTask =
        ViewTask(
            id = domainTask.id,
            title = domainTask.title,
            dueDate = domainTask.dueDate,
            isCompleted = domainTask.completed
        )

}