package com.composeit.local.datasource

import com.composeit.local.mapper.CategoryMapper
import com.composeit.local.provider.DaoProvider
import com.composeit.repository.datasource.CategoryDataSource
import com.composeit.repository.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class CategoryLocalDataSource(
    private val categoryMapper: CategoryMapper,
    daoProvider: DaoProvider
): CategoryDataSource {

    private val categoryDao = daoProvider.getCategoryDao()

    override suspend fun insertCategory(category: Category) =
        categoryDao.insertCategory(categoryMapper.toLocal(category))

    override suspend fun insertCategory(category: List<Category>) =
        categoryDao.insertCategory(categoryMapper.toLocal(category))

    override suspend fun updateCategory(category: Category) =
        categoryDao.updateCategory(categoryMapper.toLocal(category))

    override suspend fun deleteCategory(category: Category) =
        categoryDao.deleteCategory(categoryMapper.toLocal(category))

    override suspend fun cleanTable() =
        categoryDao.cleanTable()

    override fun findAllCategories(): Flow<List<Category>> =
        categoryDao.findAllCategories().map { categoryMapper.toRepo(it) }

    override suspend fun findCategoryById(categoryId: Long): Category? =
        categoryDao.findCategoryById(categoryId)?.let { categoryMapper.toRepo(it) }
}