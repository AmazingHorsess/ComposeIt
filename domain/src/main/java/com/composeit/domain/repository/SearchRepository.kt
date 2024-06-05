package com.composeit.domain.repository

import com.composeit.domain.model.Task
import com.composeit.domain.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    suspend fun findTaskById(query: String): Flow<List<TaskWithCategory>>
}