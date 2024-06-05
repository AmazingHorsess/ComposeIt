package com.composeit.repository.datasource

import com.composeit.repository.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow

interface TaskWithCategoryDataSource {

    /**
     * Get all inserted tasks with category.
     *
     * @return all inserted tasks with category
     */
    fun findAllTasksWithCategory(): Flow<List<TaskWithCategory>>

    /**
     * Get all inserted tasks related with the given category.
     *
     * @param categoryId the category id
     *
     * @return all inserted tasks with category
     */
    fun findAllTasksWithCategoryId(categoryId: Long): Flow<List<TaskWithCategory>>
}