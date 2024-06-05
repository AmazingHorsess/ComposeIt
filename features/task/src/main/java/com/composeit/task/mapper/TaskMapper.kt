package com.composeit.task.mapper

import com.composeit.task.model.Task as ViewTask
import com.composeit.domain.model.Task as DomainTask

internal class TaskMapper(
    private val alarmIntervalMapper: AlarmIntervalMapper
) {

    fun toView(domainTask: DomainTask): ViewTask =
       ViewTask(
           id = domainTask.id,
           completed = domainTask.completed,
           title = domainTask.title,
           description = domainTask.description,
           dueDate = domainTask.dueDate,
           categoryId = domainTask.categoryId,
           creationDate = domainTask.creationDate,
           completedDate = domainTask.completedDate,
           isRepeating = domainTask.isRepeating,
           alarmInterval = alarmIntervalMapper.toView(domainTask.alarmInterval),
       )
}