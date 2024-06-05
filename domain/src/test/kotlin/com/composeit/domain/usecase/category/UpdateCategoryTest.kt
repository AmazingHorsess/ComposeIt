package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category
import com.composeit.domain.usecase.category.implementation.AddCategoryImpl
import com.composeit.domain.usecase.category.implementation.LoadCategoryImpl
import com.composeit.domain.usecase.category.implementation.UpdateCategoryImpl
import com.composeit.domain.usecase.fake.CategoryRepositoryFake
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class UpdateCategoryTest {

    private val categoryRepository = CategoryRepositoryFake()

    private val addCategoryUseCase = AddCategoryImpl(categoryRepository)

    private val loadCategoryUseCase = LoadCategoryImpl(categoryRepository)

    private val updateCategoryUseCase = UpdateCategoryImpl(categoryRepository)

    @Before
    fun setup() = runTest{
        categoryRepository.cleanTable()

    }

    @Test
    fun `test if category is updated`() = runTest {
        val category = Category(id = 25, "Test Category", "#0434F")
        addCategoryUseCase(category)

        val updatedCategory = category.copy(name=" New Name", color =  "#523FF")
        updateCategoryUseCase(updatedCategory)

        val result = loadCategoryUseCase(category.id)
        Assert.assertEquals(updatedCategory, result)
    }
}