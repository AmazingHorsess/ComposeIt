package com.composeit.local.converter

import androidx.room.TypeConverter
import com.composeit.local.model.AlarmInterval

internal class AlarmIntervalConverter {

    @TypeConverter
    fun toAlarmInterval(id: Int?): AlarmInterval? =
        AlarmInterval.entries.toTypedArray().find { it.id == id }

    @TypeConverter
    fun toId(alarmInterval: AlarmInterval?): Int? =
        alarmInterval?.id

}