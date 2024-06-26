package com.composeit.repository.datasource

import com.composeit.repository.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {

    /**
     * Gets tasks based on the given name.
     *
     * @param query the name to query
     *
     * @return the list of tasks that match the given query
     */
    suspend fun findTaskByName(query: String): Flow<List<TaskWithCategory>>
}