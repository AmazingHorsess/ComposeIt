package com.composeit.repository

import com.composeit.domain.model.TaskWithCategory
import com.composeit.domain.repository.SearchRepository
import com.composeit.repository.datasource.CategoryDataSource
import com.composeit.repository.datasource.SearchDataSource
import com.composeit.repository.mapper.TaskWithCategoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SearchRepositoryImpl(
    private val dataSource: SearchDataSource,
    private val mapper: TaskWithCategoryMapper
) : SearchRepository{

    override suspend fun findTaskById(query: String): Flow<List<TaskWithCategory>> =
        dataSource.findTaskByName(query).map { mapper.toDomain(it) }
}