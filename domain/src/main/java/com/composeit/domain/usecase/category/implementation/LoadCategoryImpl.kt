package com.composeit.domain.usecase.category.implementation

import com.composeit.domain.model.Category
import com.composeit.domain.repository.CategoryRepository
import com.composeit.domain.usecase.category.LoadAllCategories
import com.composeit.domain.usecase.category.LoadCategory

internal class LoadCategoryImpl(
    private val categoryRepository: CategoryRepository
): LoadCategory{
    override suspend fun invoke(categoryId: Long): Category? =
        categoryRepository.findCategoryById(categoryId)


}