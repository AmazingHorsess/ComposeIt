package com.composeit.domain.usecase.alarm

import com.composeit.domain.model.Task
import com.composeit.domain.usecase.alarm.implementation.ScheduleAlarmImpl
import com.composeit.domain.usecase.fake.AlarmInteractorFake
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import com.composeit.domain.usecase.task.implementatiom.LoadTaskImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.Calendar

internal class ScheduleAlarmTest {

    private val taskRepository = TaskRepositoryFake()

    private val alarmInteractor = AlarmInteractorFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val addTaskUseCase = AddTaskImpl(taskRepository, glanceInteractor)

    private val getTaskUseCase = LoadTaskImpl(taskRepository)

    private val scheduleAlarmUseCase = ScheduleAlarmImpl(taskRepository, alarmInteractor)

    @Before
    fun setup() = runTest {
        taskRepository.cleanTable()
        alarmInteractor.clear()
    }

    @Test
    fun `test if alarm is scheduled`() = runTest {
        val task = Task(id = 1, title = "I need a alarm here")
        val alarm = Calendar.getInstance()
        addTaskUseCase(task)

        scheduleAlarmUseCase(task.id, alarm)
        val result = getTaskUseCase(task.id)
        val assertTask = task.copy(dueDate = alarm)

        Assert.assertEquals(assertTask, result)
    }
}