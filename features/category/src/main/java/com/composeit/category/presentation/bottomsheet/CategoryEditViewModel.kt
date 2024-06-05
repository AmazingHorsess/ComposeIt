package com.composeit.category.presentation.bottomsheet

import androidx.lifecycle.ViewModel
import com.amazinghorsess.category_api.model.Category
import com.composeit.category.mapper.CategoryMapper
import com.composeit.category.presentation.bottomsheet.state.CategorySheetState
import com.composeit.domain.usecase.category.DeleteCategory
import com.composeit.domain.usecase.category.LoadCategory
import com.composeit.domain.usecase.category.UpdateCategory
import com.libraries.core.coroutines.AppCoroutineScope
import kotlinx.coroutines.flow.flow

internal class CategoryEditViewModel(
    private val loadCategoryUseCase: LoadCategory,
    private val updateCategoryUseCase: UpdateCategory,
    private val deleteCategoryUseCase: DeleteCategory,
    private val applicationScope: AppCoroutineScope,
    private val categoryMapper: CategoryMapper
    ): ViewModel() {

        fun loadCategory(categoryId: Long) = flow{
            val category = loadCategoryUseCase(categoryId)
            if (category != null){
                val viewCategory = categoryMapper.toView(category)
                emit(CategorySheetState.Loaded(viewCategory))
            } else{
                emit(CategorySheetState.Empty)
            }
        }

        fun updateCategory(category: Category){
            if (category.name.isEmpty()) return
            applicationScope.launch {
                val domainCategory = categoryMapper.toDomain(category)
                updateCategoryUseCase(domainCategory)
            }
        }

        fun deleteCategory(category: Category) = applicationScope.launch {
            val domainCategory = categoryMapper.toDomain(category)
            deleteCategoryUseCase(domainCategory)
        }
}

