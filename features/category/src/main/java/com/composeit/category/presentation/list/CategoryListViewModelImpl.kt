package com.composeit.category.presentation.list

import com.amazinghorsess.category_api.presentation.CategoryListViewModel
import com.amazinghorsess.category_api.presentation.CategoryState
import com.composeit.category.mapper.CategoryMapper
import com.composeit.domain.usecase.category.LoadAllCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class CategoryListViewModelImpl(
    private val loadAllCategoriesUseCase: LoadAllCategories,
    private val categoryMapper: CategoryMapper
): CategoryListViewModel() {

    override fun loadCategories(): Flow<CategoryState> = flow {
        loadAllCategoriesUseCase().collect{ categoryList ->
            if(categoryList.isNotEmpty()){
                val mappedList = categoryMapper.toView(categoryList)
                emit(CategoryState.Loaded(mappedList))
            } else {
                emit(CategoryState.Empty)
            }

        }
    }


}