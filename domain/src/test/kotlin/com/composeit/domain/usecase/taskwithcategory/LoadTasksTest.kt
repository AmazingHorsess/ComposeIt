package com.composeit.domain.usecase.taskwithcategory

import com.composeit.domain.model.Category
import com.composeit.domain.model.Task
import com.composeit.domain.usecase.fake.CategoryRepositoryFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.fake.TaskWithCategoryRepositoryFake
import com.composeit.domain.usecase.taskwithcategory.implementation.LoadUncompletedTasksImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class LoadTasksTest {

    private val taskRepository = TaskRepositoryFake()

    private val categoryRepository = CategoryRepositoryFake()

    private val taskWithCategoryRepository = TaskWithCategoryRepositoryFake(taskRepository, categoryRepository)

    private val loadCompletedTasksUseCase = LoadCompletedTasks(taskWithCategoryRepository)

    private val loadUncompletedTasksUseCase = LoadUncompletedTasksImpl(taskWithCategoryRepository)

    @Before
    fun setup() = runTest {
        val category1 = Category(id = 1, name = "cat1", color = "#FFFFFF")
        val category2 = Category(id = 2, name = "cat2", color = "#000000")
        categoryRepository.insertCategory(listOf(category1, category2))

        val task1 = Task(1, title = "Task 1", completed = false, categoryId = category2.id)
        val task2 = Task(2, title = "Task 2", completed = true, categoryId = category2.id)
        val task3 = Task(3, title = "Task 3", completed = true, categoryId = category1.id)
        val task4 = Task(4, title = "Task 4", completed = false)
        val taskList = listOf(task1, task2, task3, task4)
        taskList.forEach { task -> taskRepository.insertTask(task) }
    }

    @After
    fun tearDown() = runTest {
        taskRepository.cleanTable()
        categoryRepository.cleanTable()
    }

    @Test
    fun `test if completed tasks are filtered`() = runTest {
        val completedTasks = loadCompletedTasksUseCase().first()

        Assert.assertEquals(2, completedTasks.size)
        completedTasks.forEach { taskWithCategory ->
            Assert.assertTrue(taskWithCategory.task.completed)
        }
    }

    @Test
    fun `test if uncompleted tasks are filtered`() = runTest {
        val uncompletedTasks = loadUncompletedTasksUseCase().first()

        Assert.assertEquals(2, uncompletedTasks.size)
        uncompletedTasks.forEach { taskWithCategory ->
            Assert.assertFalse(taskWithCategory.task.completed)
        }
    }

    @Test
    fun `test if uncompleted tasks are filtered by category`() = runTest {
        val uncompletedTasksByCategory = loadUncompletedTasksUseCase(2L).first()

        Assert.assertEquals(1, uncompletedTasksByCategory.size)
        uncompletedTasksByCategory.forEach { taskWithCategory ->
            Assert.assertFalse(taskWithCategory.task.completed)
        }
    }
}