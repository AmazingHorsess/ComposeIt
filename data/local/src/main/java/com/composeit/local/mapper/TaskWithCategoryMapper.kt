package com.composeit.local.mapper

import com.composeit.repository.model.TaskWithCategory
import com.composeit.local.model.TaskWithCategory as LocalTaskWithCategory
import com.composeit.repository.model.TaskWithCategory as RepoTaskWithCategory

internal class TaskWithCategoryMapper(
    private val categoryMapper: CategoryMapper,
    private val taskMapper: TaskMapper
) {

    fun toLocal(repoTaskWithCategory: RepoTaskWithCategory): LocalTaskWithCategory =
        LocalTaskWithCategory(
            task = taskMapper.toLocal(repoTaskWithCategory.task),
            category = repoTaskWithCategory.category?.let { categoryMapper.toLocal(it) }
        )

    fun toLocal(localTaskListWithCategory: List<RepoTaskWithCategory>): List<LocalTaskWithCategory> =
        localTaskListWithCategory.map { toLocal(it) }

    fun toRepo(localTaskList: List<LocalTaskWithCategory>): List<RepoTaskWithCategory> =
        localTaskList.map { toRepo(it) }

    private fun toRepo(localTask: LocalTaskWithCategory): RepoTaskWithCategory =
        RepoTaskWithCategory(
            task = taskMapper.toRepo(localTask.task),
            category = localTask.category?.let { categoryMapper.toRepo(it) },
        )
}