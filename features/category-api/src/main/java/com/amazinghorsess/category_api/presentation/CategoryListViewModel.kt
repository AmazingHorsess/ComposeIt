package com.amazinghorsess.category_api.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class CategoryListViewModel: ViewModel() {

    abstract fun loadCategories(): Flow<CategoryState>

}