package com.composeit.local.mapper

import com.composeit.local.model.Task as LocalTask
import com.composeit.repository.model.Task as RepoTask

internal class TaskMapper(
    private val alarmIntervalMapper: AlarmIntervalMapper
) {

    fun toLocal(repoTask: RepoTask): LocalTask =
            LocalTask(
                id = repoTask.id,
                completed = repoTask.completed,
                dueDate = repoTask.dueDate,
                completedDate = repoTask.completedDate,
                title = repoTask.title,
                description = repoTask.description,
                categoryId = repoTask.categoryId,
                isRepeating = repoTask.isRepeating,
                creationDate = repoTask.creationDate,
                alarmInterval = repoTask.alarmInterval?.let { alarmIntervalMapper.toLocal(it) }
            )

    fun toRepo(localTask: LocalTask): RepoTask =
        RepoTask(
            id = localTask.id,
            completed = localTask.completed,
            dueDate = localTask.dueDate,
            title = localTask.title,
            description = localTask.description,
            completedDate = localTask.completedDate,
            categoryId = localTask.categoryId,
            isRepeating = localTask.isRepeating,
            creationDate = localTask.creationDate,
            alarmInterval = localTask.alarmInterval?.let { alarmIntervalMapper.toRepo(it) }
        )


}