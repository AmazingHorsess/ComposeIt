package com.composeit.domain.usecase.alarm

import com.composeit.domain.model.Task
import com.composeit.domain.usecase.alarm.implementation.CancelAlarmImpl
import com.composeit.domain.usecase.fake.AlarmInteractorFake
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class CancelAlarmTest {

    private val taskRepository = TaskRepositoryFake()

    private val alarmInteractor = AlarmInteractorFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val cancelAlarmUseCase = CancelAlarmImpl(alarmInteractor,taskRepository)

    private val addTaskUseCase = AddTaskImpl(taskRepository,glanceInteractor)

    private val loadTaskUseCase = LoadTaskImpl(taskRepository)

    @Before
    fun setup() = runTest {
        taskRepository.cleanTable()
        alarmInteractor.clear()
        glanceInteractor.clean()
    }

    @Test
    fun `test if alarm was cancelled`() = runTest {
        val task = Task(
            id = 15,
            title = "Test title",
            description = "test description"
        )
        addTaskUseCase(task)
        cancelAlarmUseCase(task.id)

        Assert.assertFalse(alarmInteractor.isAlarmScheduled(task.id))

    }

    @Test
    fun `test if alarm is removed from task`() = runTest {
        val task = Task(
            id = 15,
            title = "Test title",
            description = "test description"
        )
        addTaskUseCase(task)
        cancelAlarmUseCase(task.id)

        val result = loadTaskUseCase(task.id)
        require(result != null)
        Assert.assertNull(result.dueDate)
    }

}