package com.composeit.task.presentation.detail.alarm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.composeit.task.model.AlarmInterval
import java.util.Calendar

internal class AlarmState(
    calendar: Calendar?,
    alarmInterval: AlarmInterval?
){
    var date by mutableStateOf(calendar)

    var alarmInterval by mutableStateOf(alarmInterval)

    var showExactAlarmDialog by mutableStateOf(false)

    var showNotificationDialog by mutableStateOf(false)

    var showRationaleDialog by mutableStateOf(false)
}
@Composable
internal fun rememberAlarmState(calendar: Calendar?,alarmInterval: AlarmInterval?) =
    remember{AlarmState(calendar,alarmInterval)}