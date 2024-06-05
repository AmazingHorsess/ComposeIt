package com.composeit.repository.mapper

import com.composeit.domain.model.Task as DomainTask
import com.composeit.repository.model.Task as RepoTask

internal class TaskMapper(
    private val alarmIntervalMapper: AlarmIntervalMapper
) {
    fun toDomain(repoTask: RepoTask): DomainTask =
        DomainTask(
            id = repoTask.id,
            completed = repoTask.completed,
            title = repoTask.title,
            description = repoTask.description,
            categoryId = repoTask.categoryId,
            dueDate = repoTask.dueDate,
            creationDate = repoTask.creationDate,
            completedDate = repoTask.completedDate,
            isRepeating = repoTask.isRepeating,
            alarmInterval = repoTask.alarmInterval?.let { alarmIntervalMapper.toDomain(it) },
        )

    fun toRepo(domainTaskList: List<DomainTask>): List<RepoTask> =
        domainTaskList.map { toRepo(it) }

    fun toRepo(domainTask: DomainTask): RepoTask =
        RepoTask(
            id = domainTask.id,
            completed = domainTask.completed,
            title = domainTask.title,
            description = domainTask.description,
            categoryId = domainTask.categoryId,
            dueDate = domainTask.dueDate,
            creationDate = domainTask.creationDate,
            completedDate = domainTask.completedDate,
            isRepeating = domainTask.isRepeating,
            alarmInterval = domainTask.alarmInterval?.let { alarmIntervalMapper.toRepo(it) },
        )

}