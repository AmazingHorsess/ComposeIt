package com.composeit.domain.usecase.task

import com.composeit.domain.model.Task
import com.composeit.domain.usecase.fake.AlarmInteractorFake
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class DeleteTaskTest {

    private val taskRepository = TaskRepositoryFake()

    private val alarmInteractor = AlarmInteractorFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val deleteTaskUseCase = DeleteTask(alarmInteractor,taskRepository)

    private val loadTaskUseCase = LoadTaskImpl(taskRepository)

    private val addTaskUseCase = AddTaskImpl(taskRepository, glanceInteractor )

    @Before
    fun setup() = runTest {
        taskRepository.cleanTable()
        alarmInteractor.clear()
        glanceInteractor.clean()
    }

    @Test
    fun `test if task is deleted`() = runTest {
        val task = Task(
            id = 15,
            title = "test title"
        )
        addTaskUseCase(task)
        deleteTaskUseCase(task)

        val loadedTask = loadTaskUseCase(task.id)

        Assert.assertNull(loadedTask)

    }

    @Test
    fun `test if the alarm is canceled when the task is completed`() = runTest {
        val task = Task(
            id = 15,
            title = "test title"
        )
        addTaskUseCase(task)
        deleteTaskUseCase(task)

        Assert.assertFalse(alarmInteractor.isAlarmScheduled(task.id))

    }

    @Test
    fun `test if the glance was notified`() = runTest {
        val task = Task(
            id = 15,
            title = "test title",
            description = "this desc"
        )
        addTaskUseCase(task)

        Assert.assertTrue(glanceInteractor.wasNotified)
    }
}



