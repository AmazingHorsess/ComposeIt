package com.composeit.alarm.permission

internal interface PermissionChecker {

    fun checkPermission(permission: String): Boolean

    fun canScheduleExactAlarms(): Boolean
}