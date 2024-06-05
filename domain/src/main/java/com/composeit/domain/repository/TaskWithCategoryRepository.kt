package com.composeit.domain.repository

import com.composeit.domain.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow

interface TaskWithCategoryRepository {

    fun findAllTasksWithCategory(): Flow<List<TaskWithCategory>>

    fun findAllTasksWithCategoryId(categoryId: Long): Flow<List<TaskWithCategory>>
}