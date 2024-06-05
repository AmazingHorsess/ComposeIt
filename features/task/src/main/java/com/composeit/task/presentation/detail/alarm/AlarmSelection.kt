package com.composeit.task.presentation.detail.alarm

import android.Manifest
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amazinghorsess.task.R
import com.composeit.task.model.AlarmInterval
import com.composeit.task.permission.PermissionStateFactory
import com.composeit.task.presentation.detail.TaskDetailSectionContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.libraries.core.ui.DateTimePickerDialog

import java.util.Calendar

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun AlarmSelection(
    calendar: Calendar?,
    interval: AlarmInterval?,
    onAlarmUpdate: (Calendar?) -> Unit,
    onIntervalSelect: (AlarmInterval) -> Unit,
    hasAlarmPermission: () -> Boolean,
    shouldCheckNotificationPermission:Boolean,
){
    val context = LocalContext.current
    val permissionState = if(shouldCheckNotificationPermission){
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    } else {
        PermissionStateFactory.getGrantedPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    }
    val state = rememberAlarmState(calendar = calendar, alarmInterval = interval)

    AlarmPermissionDialog(
        context = context,
        isDialogOpen = state.showExactAlarmDialog,
        onCloseDialog = {state.showExactAlarmDialog =false}
    )

    NotificationPermissionDialog(
        permissionState = permissionState,
        isDialogOpen = state.showNotificationDialog,
        onCloseDialog = { state.showNotificationDialog = false },
    )

    RationalePermissionDialog(
        context = context,
        isDialogOpen = state.showRationaleDialog,
        onCloseDialog = { state.showRationaleDialog = false },
    )
    
    AlarmSelectionContent(
        context = context,
        state = state,
        permissionState = permissionState,
        hasAlarmPermission = hasAlarmPermission,
        onAlarmUpdate = onAlarmUpdate,
        onIntervalSelect = onIntervalSelect,
    )
        


}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun AlarmSelectionContent(
    context: Context,
    state: AlarmState,
    permissionState: PermissionState,
    hasAlarmPermission: () -> Boolean,
    onAlarmUpdate: (Calendar?) -> Unit,
    onIntervalSelect: (AlarmInterval) -> Unit
){
    Column {
        TaskDetailSectionContent(
            modifier = Modifier
                .height(56.dp)
                .clickable {
                    when {
                        hasAlarmPermission() && permissionState.status.isGranted ->
                            DateTimePickerDialog(context) { calendar ->
                                state.date = calendar
                                onAlarmUpdate(calendar)
                            }.show()

                        permissionState.status.shouldShowRationale ->
                            state.showRationaleDialog = true

                        else -> {
                            state.showExactAlarmDialog = !hasAlarmPermission()
                            state.showNotificationDialog = !permissionState.status.isGranted
                        }
                    }
                },
            imageVector = Icons.Outlined.Alarm,
            contentDescription = R.string.task_detail_cd_icon_alarm,
        ) {
            AlarmInfo(state.date) {
                state.date = null
                onAlarmUpdate(null)
            }
        }
        AlarmIntervalSelection(
            date = state.date,
            alarmInterval = state.alarmInterval,
            onIntervalSelect = { interval ->
                state.alarmInterval = interval
                onIntervalSelect(interval)
            },
        )
    }

}
