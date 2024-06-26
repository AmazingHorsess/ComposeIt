package com.composeit.domain.usecase.category

import com.composeit.domain.model.Category
import com.composeit.domain.usecase.category.implementation.AddCategoryImpl
import com.composeit.domain.usecase.category.implementation.DeleteCategoryImpl
import com.composeit.domain.usecase.category.implementation.LoadCategoryImpl
import com.composeit.domain.usecase.fake.CategoryRepositoryFake
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class DeleteCategoryTest {

    private val categoryRepository = CategoryRepositoryFake()

    private val addCategory = AddCategoryImpl(categoryRepository)

    private val deleteCategory = DeleteCategoryImpl(categoryRepository)

    private val loadCategory = LoadCategoryImpl(categoryRepository)

    @Before
    fun setup() = runTest {
        categoryRepository.cleanTable()
    }

    @Test
    fun `test if category is deleted`() = runTest {
        val category = Category(id = 13, name = "books to read", color = "#FFAA00")
        addCategory(category)
        deleteCategory(category)

        val result = loadCategory(category.id)
        Assert.assertNull(result)
    }
}