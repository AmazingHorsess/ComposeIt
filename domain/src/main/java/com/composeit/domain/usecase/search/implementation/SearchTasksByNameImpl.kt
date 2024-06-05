package com.composeit.domain.usecase.search.implementation

import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.SearchRepository
import com.composeit.domain.usecase.search.SearchTaskByName
import kotlinx.coroutines.flow.Flow

internal class SearchTasksByNameImpl(
    private val searchRepository: SearchRepository
): SearchTaskByName {
    override suspend fun invoke(query: String): Flow<List<TaskWithCategory>> =
        searchRepository.findTaskById(query)
}