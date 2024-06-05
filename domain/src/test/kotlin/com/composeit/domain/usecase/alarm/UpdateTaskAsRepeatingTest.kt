package com.composeit.domain.usecase.alarm

import com.composeit.domain.model.AlarmInterval
import com.composeit.domain.model.Task
import com.composeit.domain.usecase.alarm.implementation.UpdateTaskAsRepeatingImpl
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class UpdateTaskAsRepeatingTest {

    private val taskRepository = TaskRepositoryFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val addTaskUseCase = AddTaskImpl(taskRepository, glanceInteractor)

    private val getTaskUseCase = LoadTaskImpl(taskRepository)

    private val scheduleRepeatingUseCase = UpdateTaskAsRepeatingImpl(taskRepository)

    @Before
    fun setup() = runTest {
        taskRepository.cleanTable()
    }

    @Test
    fun `test if task is updated with new interval`() = runTest {
        val task = Task(id = 984L, title = "I'll be there for you")
        addTaskUseCase(task)
        val interval = AlarmInterval.WEEKLY

        scheduleRepeatingUseCase(task.id, interval)

        val result = getTaskUseCase(task.id)
        require(result != null)
        Assert.assertEquals(interval, result.alarmInterval)
        Assert.assertTrue(result.isRepeating)
    }

    @Test
    fun `test if repeating state is cleared when update alarm interval to never`() = runTest {
        val task = Task(
            id = 984L,
            title = "nanana",
            alarmInterval = AlarmInterval.YEARLY,
            isRepeating = true,
        )

        addTaskUseCase(task)

        scheduleRepeatingUseCase(task.id, null)

        val result = getTaskUseCase(task.id)
        require(result != null)
        Assert.assertEquals(null, result.alarmInterval)
        Assert.assertFalse(result.isRepeating)
    }
}