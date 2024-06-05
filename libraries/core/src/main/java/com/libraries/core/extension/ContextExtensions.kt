package com.libraries.core.extension

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.annotation.ColorRes
import androidx.core.app.AlarmManagerCompat
import androidx.core.net.toUri
import java.util.Calendar

@SuppressLint("ResourceType")
fun Context.getStringColor(@ColorRes colorRes: Int): String =
    resources.getString(colorRes)

fun Context.setExactAlarm(
    triggerAtMills:Long,
    operation: PendingIntent?,
    type: Int = AlarmManager.RTC_WAKEUP
){
    val currentTime = Calendar.getInstance().timeInMillis
    if (triggerAtMills <= currentTime){
        Log.d("ContextAlarmManager","It is noot possible to set an alarm in the past")
        return
    }

    if (operation == null){
        Log.d("ContextAlarmManager","PendingIntent cannot be null")
        return
    }

    val manager = getAlarmManager()
    manager?.let {
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            it,
            type,
            triggerAtMills,
            operation
        )
    }



}

fun Context.openUrl(url: String) {
    with(Intent(Intent.ACTION_VIEW)) {
        this.data = url.toUri()
        startActivity(this)
    }
}

fun Context.cancelAlarm(operation: PendingIntent?) {
    if (operation == null) {
        return
    }

    val manager = getAlarmManager()
    manager?.let { manager.cancel(operation) }
}