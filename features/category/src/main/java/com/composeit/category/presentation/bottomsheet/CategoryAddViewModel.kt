package com.composeit.category.presentation.bottomsheet

import androidx.lifecycle.ViewModel
import com.amazinghorsess.category_api.model.Category
import com.composeit.category.mapper.CategoryMapper
import com.composeit.domain.usecase.category.AddCategory
import com.libraries.core.coroutines.AppCoroutineScope

internal class CategoryAddViewModel(
    private val addCategoryUseCase: AddCategory,
    private val applicationScope: AppCoroutineScope,
    private val categoryMapper: CategoryMapper,

): ViewModel() {

    fun addCategory(category: Category){
        if (category.name.isEmpty()) return

        applicationScope.launch {
            val domainCategory = categoryMapper.toDomain(category)
            addCategoryUseCase.invoke(domainCategory)

        }
    }
}