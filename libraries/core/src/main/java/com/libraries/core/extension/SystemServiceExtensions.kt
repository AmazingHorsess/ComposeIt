package com.libraries.core.extension

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context

fun Context.getNotificationManager() =
    getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

fun Context.getAlarmManager() =
    getSystemService(Context.ALARM_SERVICE) as? AlarmManager
