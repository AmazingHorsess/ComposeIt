package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskCategoryImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskDescriptionImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskImpl
import com.composeit.domain.usecase.task.implementatiom.UpdateTaskTitleImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class UpdateTaskTest {

    private val taskRepository = TaskRepositoryFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val addTaskUseCase = AddTaskImpl(taskRepository,glanceInteractor)

    private val updateTaskUseCase = UpdateTaskImpl(taskRepository,glanceInteractor)

    private val loadTaskUseCase = LoadTaskImpl(taskRepository)

    private val updateTitleUseCase = UpdateTaskTitleImpl(loadTaskUseCase,updateTaskUseCase,glanceInteractor)

    private val updateTaskDescriptionUseCase = UpdateTaskDescriptionImpl(loadTaskUseCase,updateTaskUseCase)

    private val updateTaskCategoryUseCase = UpdateTaskCategoryImpl(loadTaskUseCase,updateTaskUseCase)


    @Before
    fun setup(){
        glanceInteractor.clean()
    }

    @Test
    fun `test if task is updated`() = runTest {
        val task = Task(
            id = 15,
            title = "test title",
            description = "description",
        )
        addTaskUseCase(task)

        val updatedTask = task.copy(id = 15, title = "updated title", description = "description")
        updateTaskUseCase(updatedTask)

        val loadedTask = loadTaskUseCase(task.id)
        Assert.assertEquals(updatedTask,loadedTask)

    }

    @Test
    fun `test if task title is updated`() = runTest {
        val task = Task(id = 15,
            title = "test title",
            description = "test description"
        )
        addTaskUseCase(task)

        val newTitle = "Updated title"
        updateTitleUseCase(task.id, newTitle)

        val loadedTask = loadTaskUseCase(task.id)
        Assert.assertEquals(newTitle,loadedTask!!.title)

    }

    @Test
    fun `test if description is updated`() = runTest {
        val task = Task(
            id = 15,
            title = "test title",
            description = "test description"
        )
        addTaskUseCase(task)

        val newDescription = "new description"
        updateTaskDescriptionUseCase(task.id, newDescription)

        val loadedTask = loadTaskUseCase(task.id)
        Assert.assertEquals(newDescription, loadedTask!!.description)
    }

    @Test
    fun `test if category is updated`() = runTest {
        val task = Task(
            id = 15,
            title = "test title",
            description = "test description",
            categoryId = null
        )
        addTaskUseCase(task)

        val newCategory = 10L
        updateTaskCategoryUseCase(task.id,newCategory)

        val loadedTask = loadTaskUseCase(task.id)
        Assert.assertEquals(newCategory, loadedTask!!.categoryId)
    }

    @Test
    fun `test if glance was notified`() = runTest {
        val task = Task(
            id = 15,
            title = "test title",
            description = "test description",
            categoryId = null
        )
        addTaskUseCase(task)
        Assert.assertTrue(glanceInteractor.wasNotified)
    }


}
