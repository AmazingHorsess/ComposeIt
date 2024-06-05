package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class AddTaskTest {

    private val taskRepository = TaskRepositoryFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val addTaskUseCase = AddTaskImpl(taskRepository, glanceInteractor)

    private val getTaskUseCase = LoadTaskImpl(taskRepository)


    @Before
    fun setup() = runTest {
        taskRepository.cleanTable()
        glanceInteractor.clean()
    }

    @Test
    fun `test if task correctly added`() = runTest {
        val task = Task(
            id = 15,
            title = "test tittle",
            description = "test decription",

        )
        addTaskUseCase(task)
        val result = getTaskUseCase(task.id)

        Assert.assertNotNull(result)
    }

    @Test
    fun `test if task with empty title not added`() = runTest {
        val task = Task(
            id = 15,
            title = " ",
            description = "test description"
        )
        addTaskUseCase(task)

        val result = getTaskUseCase(task.id)

        Assert.assertNull(result)
    }

    @Test
    fun `test if glance was notified`() = runTest {
        val task = Task(
            id = 15,
            title = "test title",
            description = "test description"
        )
        addTaskUseCase(task)
        Assert.assertTrue(glanceInteractor.wasNotified)
    }
}