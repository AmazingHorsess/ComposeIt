package com.composeit.local.mapper

import com.composeit.repository.model.AlarmInterval as RepoInterval
import com.composeit.local.model.AlarmInterval as LocalInterval


internal class AlarmIntervalMapper {

    fun toRepo(alarmInterval: LocalInterval): RepoInterval =
        when(alarmInterval){
            LocalInterval.HOURLY -> RepoInterval.HOURLY
            LocalInterval.DAILY -> RepoInterval.DAILY
            LocalInterval.WEEKLY -> RepoInterval.WEEKLY
            LocalInterval.MONTHLY -> RepoInterval.MONTHLY
            LocalInterval.YEARLY -> RepoInterval.YEARLY

        }

    fun toLocal(alarmInterval: RepoInterval): LocalInterval =
        when(alarmInterval){
            RepoInterval.HOURLY -> LocalInterval.HOURLY
            RepoInterval.DAILY -> LocalInterval.DAILY
            RepoInterval.WEEKLY -> LocalInterval.WEEKLY
            RepoInterval.MONTHLY -> LocalInterval.MONTHLY
            RepoInterval.YEARLY -> LocalInterval.YEARLY
        }
}