package com.composeit.alarm.permission

import android.Manifest
import android.os.Build
import com.composeit.alarmapi.AlarmPermission

internal class AlarmPermissionImpl(
    private val permissionChecker: PermissionChecker,
    private val androidVersion: AndroidVersion,

): AlarmPermission {

    override fun hasExactAlarmPermission(): Boolean =
        if (androidVersion.currentVersion >= Build.VERSION_CODES.S){
            permissionChecker.canScheduleExactAlarms()
        } else{
            true
        }


    override fun hasNotificationPermission(): Boolean =
        if (androidVersion.currentVersion >= Build.VERSION_CODES.TIRAMISU){
            permissionChecker.checkPermission(Manifest.permission.POST_NOTIFICATIONS)
        } else{
            true
        }

    override fun shouldCheckNotificationPermission(): Boolean =
        androidVersion.currentVersion >= Build.VERSION_CODES.TIRAMISU
}