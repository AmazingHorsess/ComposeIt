package com.composeit.domain.usecase.search

import com.composeit.domain.model.TaskWithCategory
import kotlinx.coroutines.flow.Flow

interface SearchTaskByName {

    suspend operator fun  invoke(query: String): Flow<List<TaskWithCategory>>
}