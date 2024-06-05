package com.composeit.domain.usecase.fake

import com.composeit.domain.interactor.AlarmInteractor

internal class AlarmInteractorFake: AlarmInteractor {
    private val alarmMap: MutableMap<Long, Long> = mutableMapOf()

    override fun schedule(alarmId: Long, timeMillis: Long) {
        alarmMap[alarmId] = timeMillis
    }

    override fun cancel(alarmId: Long) {
        alarmMap.remove(alarmId)
    }

    fun isAlarmScheduled(alarmId:Long): Boolean =
        alarmMap.contains(alarmId)

    fun getAlarmTime(alarmId: Long): Long? =
        alarmMap[alarmId]

    fun clear(){
        alarmMap.clear()
    }
}