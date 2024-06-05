package com.composeit.task.presentation.detail.alarm

import androidx.lifecycle.ViewModel
import com.composeit.domain.usecase.alarm.CancelAlarm
import com.composeit.domain.usecase.alarm.ScheduleAlarm
import com.composeit.domain.usecase.alarm.UpdateTaskAsRepeating
import com.composeit.task.mapper.AlarmIntervalMapper
import com.composeit.task.model.AlarmInterval
import com.composeit.task.presentation.detail.main.TaskId
import com.libraries.core.coroutines.AppCoroutineScope
import java.util.Calendar

internal class TaskAlarmViewModel(
    private val scheduleAlarm: ScheduleAlarm,
    private val updateTaskAsRepeating: UpdateTaskAsRepeating,
    private val cancelAlarm: CancelAlarm,
    private val applicationScope: AppCoroutineScope,
    private val alarmIntervalMapper: AlarmIntervalMapper
):ViewModel() {

    fun updateAlarm(taskId: TaskId, alarm: Calendar?) = applicationScope.launch {
        if(alarm != null){
            scheduleAlarm(taskId.value, alarm)
        } else {
            cancelAlarm(taskId.value)
        }
    }

    fun setRepeating(taskId: TaskId,alarmInterval: AlarmInterval) = applicationScope.launch {
        val interval = alarmIntervalMapper.toDomain(alarmInterval)
        updateTaskAsRepeating(taskId.value, interval)
    }

}