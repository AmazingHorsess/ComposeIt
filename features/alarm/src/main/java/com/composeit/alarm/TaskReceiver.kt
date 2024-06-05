package com.composeit.alarm

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.composeit.domain.usecase.alarm.RescheduleFutureAlarms
import com.composeit.domain.usecase.alarm.ShowAlarm
import com.composeit.domain.usecase.alarm.SnoozeAlarm
import com.composeit.domain.usecase.task.CompleteTask
import com.libraries.core.coroutines.AppCoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class TaskReceiver: BroadcastReceiver(), KoinComponent {

    private val appScope: AppCoroutineScope by inject()

    private val completeTaskUseCase: CompleteTask by inject ()

    private val showAlarmUseCase: ShowAlarm by inject()

    private val snoozeAlarmUseCase: SnoozeAlarm by inject()

    private val rescheduleUseCase: RescheduleFutureAlarms by inject()


    override fun onReceive(context: Context?, intent: Intent?) {
        appScope.launch {
            handleIntent(intent)
        }
    }

    private suspend fun handleIntent(intent: Intent?) =
        when(intent?.action){
            ALARM_ACTION -> getTaskId(intent)?.let { showAlarmUseCase(it) }
            COMPLETE_ACTION -> getTaskId(intent)?.let { completeTaskUseCase(it) }
            SNOOZE_ACTION -> getTaskId(intent)?.let { snoozeAlarmUseCase(it) }
            Intent.ACTION_BOOT_COMPLETED,
            AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED,
            ->rescheduleUseCase()
            else -> Log.d("TaskReceiver","Action not supported")
        }

    private fun getTaskId(intent: Intent?) = intent?.getLongExtra(EXTRA_TASK,0)


    companion object {

        const val EXTRA_TASK = "extra_task"

        const val ALARM_ACTION = "com.composeit.SET_ALARM"

        const val COMPLETE_ACTION = "com.composeit.SET_COMPLETE"

        const val SNOOZE_ACTION = "com.composeit.SNOOZE"
    }
}