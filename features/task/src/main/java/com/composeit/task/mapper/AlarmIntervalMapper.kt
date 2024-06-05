package com.composeit.task.mapper

import android.view.View
import com.composeit.task.model.AlarmInterval
import com.composeit.domain.model.AlarmInterval as DomainAlarmInterval
import com.composeit.task.model.AlarmInterval as ViewAlarmInterval

internal class AlarmIntervalMapper{

    fun toDomain(alarmInterval: ViewAlarmInterval): DomainAlarmInterval? =
        when(alarmInterval){
            ViewAlarmInterval.HOURLY -> DomainAlarmInterval.HOURLY
            ViewAlarmInterval.DAILY -> DomainAlarmInterval.DAILY
            ViewAlarmInterval.WEEKLY -> DomainAlarmInterval.WEEKLY
            ViewAlarmInterval.MONTHLY -> DomainAlarmInterval.MONTHLY
            ViewAlarmInterval.YEARLY -> DomainAlarmInterval.YEARLY
            ViewAlarmInterval.NEVER -> null

        }
    fun toView(alarmInterval: DomainAlarmInterval?): ViewAlarmInterval =
        when(alarmInterval){
            DomainAlarmInterval.HOURLY -> ViewAlarmInterval.HOURLY
            DomainAlarmInterval.DAILY -> ViewAlarmInterval.DAILY
            DomainAlarmInterval.WEEKLY -> ViewAlarmInterval.WEEKLY
            DomainAlarmInterval.MONTHLY -> ViewAlarmInterval.MONTHLY
            DomainAlarmInterval.YEARLY -> ViewAlarmInterval.YEARLY
            null -> ViewAlarmInterval.NEVER

        }
}