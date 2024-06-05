package com.composeit.alarmapi

interface AlarmPermission {

    fun hasExactAlarmPermission(): Boolean

    fun hasNotificationPermission(): Boolean

    fun shouldCheckNotificationPermission(): Boolean

}