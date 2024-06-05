package com.composeit.domain.usecase.category.implementation

import com.composeit.domain.model.Category
import com.composeit.domain.repository.CategoryRepository
import com.composeit.domain.usecase.category.LoadAllCategories
import kotlinx.coroutines.flow.Flow

internal class LoadAllCategoriesImpl(
    private val categoryRepository: CategoryRepository
): LoadAllCategories {
    override fun invoke(): Flow<List<Category>> =
        categoryRepository.findAllCategories()


}