package com.composeit.domain.repository

import com.composeit.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

        suspend fun insertCategory(category: Category)

        suspend fun insertCategory(categoryList: List<Category>)

        suspend fun updateCategory(category: Category)

        suspend fun deleteCategory(category: Category)

        suspend fun cleanTable()

        fun findAllCategories(): Flow<List<Category>>

        suspend fun findCategoryById(categoryId: Long): Category?

}