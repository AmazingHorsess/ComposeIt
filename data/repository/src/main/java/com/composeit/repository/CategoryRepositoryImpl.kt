package com.composeit.repository

import com.composeit.domain.model.Category
import com.composeit.domain.repository.CategoryRepository
import com.composeit.repository.datasource.CategoryDataSource
import com.composeit.repository.mapper.CategoryMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CategoryRepositoryImpl(
    private val categoryDataSource: CategoryDataSource,
    private val categoryMapper: CategoryMapper,
) : CategoryRepository {


    override suspend fun insertCategory(category: Category) =
        categoryDataSource.insertCategory(categoryMapper.toRepo(category))

    override suspend fun insertCategory(categoryList: List<Category>) =
        categoryDataSource.insertCategory(categoryMapper.toRepo(categoryList))

    override suspend fun updateCategory(category: Category) =
        categoryDataSource.updateCategory(categoryMapper.toRepo(category))

    override suspend fun deleteCategory(category: Category) =
        categoryDataSource.deleteCategory(categoryMapper.toRepo(category))

    override suspend fun cleanTable() =
        categoryDataSource.cleanTable()

    override fun findAllCategories(): Flow<List<Category>> =
        categoryDataSource.findAllCategories().map {
            categoryMapper.toDomain(it)
        }

    override suspend fun findCategoryById(categoryId: Long): Category? =
        categoryDataSource.findCategoryById(categoryId)?.let { categoryMapper.toDomain(it) }

}