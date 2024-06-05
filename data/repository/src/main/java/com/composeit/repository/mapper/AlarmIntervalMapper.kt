package com.composeit.repository.mapper

import com.composeit.domain.model.AlarmInterval as DomainInterval

import com.composeit.repository.model.AlarmInterval as RepoInterval

internal class AlarmIntervalMapper {

    /**
     * Maps Alarm Interval from Repo to Domain.
     *
     * @param alarmInterval the object to be converted
     *
     * @return the converted object
     */
    fun toDomain(alarmInterval: RepoInterval): DomainInterval =
        when (alarmInterval) {
            RepoInterval.HOURLY -> DomainInterval.HOURLY
            RepoInterval.DAILY -> DomainInterval.DAILY
            RepoInterval.WEEKLY -> DomainInterval.WEEKLY
            RepoInterval.MONTHLY -> DomainInterval.MONTHLY
            RepoInterval.YEARLY -> DomainInterval.YEARLY
        }

    /**
     * Maps Alarm Interval from Domain to Repo.
     *
     * @param alarmInterval the object to be converted
     *
     * @return the converted object
     */
    fun toRepo(alarmInterval: DomainInterval): RepoInterval =
        when (alarmInterval) {
            DomainInterval.HOURLY -> RepoInterval.HOURLY
            DomainInterval.DAILY -> RepoInterval.DAILY
            DomainInterval.WEEKLY -> RepoInterval.WEEKLY
            DomainInterval.MONTHLY -> RepoInterval.MONTHLY
            DomainInterval.YEARLY -> RepoInterval.YEARLY
        }
}