package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category
import com.composeit.domain.usecase.category.implementation.AddCategoryImpl
import com.composeit.domain.usecase.category.implementation.LoadAllCategoriesImpl
import com.composeit.domain.usecase.category.implementation.LoadCategoryImpl
import com.composeit.domain.usecase.fake.CategoryRepositoryFake
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class AddCategoryTest {

    private val categoryRepository = CategoryRepositoryFake()

    private val addCategoryUseCase = AddCategoryImpl(categoryRepository)

    private val loadCategoryUseCase = LoadCategoryImpl(categoryRepository)

    private val loadAllCategoriesUseCase = LoadAllCategoriesImpl(categoryRepository)

    @Before
    fun setup() = runTest {
        categoryRepository.cleanTable()
    }

    @Test
    fun `test if category is correctly added`() = runTest {
        val category = Category(id = 22, name = "shopping list", color = "#122100")
        addCategoryUseCase(category)

        val result = loadCategoryUseCase(category.id)
        Assert.assertEquals(category, result)
    }

    @Test
    fun `test if category with empty title is not added`() = runTest {
        val category = Category(id = 44, name = "   ", color = "#876782")
        addCategoryUseCase(category)

        val result = loadCategoryUseCase(category.id)
        Assert.assertNull(result)
    }

    @Test
    fun `test if all category are added`() = runTest {
        val assertList = mutableListOf<Category>()
        for (iterator in 1..100) {
            val category = Category(id = iterator.toLong(), name = "$iterator", color = "#5567FA")
            addCategoryUseCase(category)
            assertList.add(category)
        }

        val resultList = loadAllCategoriesUseCase().first()

        Assert.assertArrayEquals(assertList.toTypedArray(), resultList.toTypedArray())
    }
}