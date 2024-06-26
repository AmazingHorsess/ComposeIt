package com.composeit.domain.usecase.alarm

import com.composeit.domain.model.AlarmInterval
import com.composeit.domain.model.Task
import com.composeit.domain.usecase.fake.AlarmInteractorFake
import com.composeit.domain.usecase.fake.CalendarProviderFake
import com.composeit.domain.usecase.fake.GlanceInteractorFake
import com.composeit.domain.usecase.fake.NotificationInteractorFake
import com.composeit.domain.usecase.fake.TaskRepositoryFake
import com.composeit.domain.usecase.task.implementatiom.AddTaskImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class ShowAlarmTest {

    private val taskRepository = TaskRepositoryFake()

    private val alarmInteractor = AlarmInteractorFake()

    private val glanceInteractor = GlanceInteractorFake()

    private val notificationInteractor = NotificationInteractorFake()

    private val calendarProvider = CalendarProviderFake()

    private val addTaskUseCase = AddTaskImpl(taskRepository,glanceInteractor)

    private val scheduleNextAlarm = ScheduleNextAlarm(taskRepository,alarmInteractor,calendarProvider)

    private val showAlarmUseCase = ShowAlarm(taskRepository,notificationInteractor,scheduleNextAlarm)

    @Before
    fun setup() = runTest {
        taskRepository.cleanTable()
        alarmInteractor.clear()
        notificationInteractor.clear()
    }

    @Test
    fun `test if alarm is shown when task is not yet completed`() = runTest {
        val task = Task(1, title = "should show", completed = false)
        addTaskUseCase(task)

        showAlarmUseCase(task.id)

        Assert.assertTrue(notificationInteractor.isNotificationShown(task.id))

    }

    @Test
    fun `test if alarm is ignored when task is already completed`() = runTest {
        val task = Task(id = 2, title = "shoud not show", completed = true)
        addTaskUseCase(task)

        showAlarmUseCase(task.id)

        Assert.assertFalse(notificationInteractor.isNotificationShown(task.id))
    }

    @Test
    fun `test if next alarm is scheduled when task is repeating`() = runTest {
        val calendar = calendarProvider.getCurrentCalendar()
        val task = Task(
            3,
            title = "should repeat",
            isRepeating = true,
            dueDate = calendar,
            alarmInterval = AlarmInterval.YEARLY,
        )
        addTaskUseCase(task)

        showAlarmUseCase(task.id)

        Assert.assertTrue(alarmInteractor.isAlarmScheduled(task.id))
    }

    @Test
    fun `test if next alarm is not scheduled when task is not repeating`() = runTest {
        val task = Task(4, title = "should not repeat", isRepeating = false)
        addTaskUseCase(task)

        showAlarmUseCase(task.id)

        Assert.assertFalse(alarmInteractor.isAlarmScheduled(task.id))
    }

    @Test
    fun `test if next alarm is not scheduled when task is completed`() = runTest {
        val task = Task(4, title = "it is already completed", isRepeating = true, completed = true)
        addTaskUseCase(task)

        showAlarmUseCase(task.id)

        Assert.assertFalse(alarmInteractor.isAlarmScheduled(task.id))
    }

}