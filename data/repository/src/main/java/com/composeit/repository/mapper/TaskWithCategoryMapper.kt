package com.composeit.repository.mapper

import com.composeit.domain.model.TaskWithCategory as DomainTaskWithCategory
import com.composeit.repository.model.TaskWithCategory as RepoTaskWithCategory

internal class TaskWithCategoryMapper(
    private val taskMapper: TaskMapper,
    private val categoryMapper: CategoryMapper
) {
    fun toDomain(localTaskList: List<RepoTaskWithCategory>): List<DomainTaskWithCategory> =
        localTaskList.map { toDomain(it) }

    fun toDomain(repoTaskWithCategory: RepoTaskWithCategory): DomainTaskWithCategory =
        DomainTaskWithCategory(
            task = taskMapper.toDomain(repoTaskWithCategory.task),
            category = repoTaskWithCategory.category?.let { categoryMapper.toDomain(it) },
        )
}