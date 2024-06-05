package com.composeit.repository.mapper
import com.composeit.repository.model.AppThemeOptions
import com.composeit.repository.model.AppThemeOptions as RepoThemeOptions
import com.composeit.domain.model.AppThemeOptions as DomainThemeOptions

internal class AppThemeOptionsMapper {

    fun toDomain(appThemeOptions: RepoThemeOptions): DomainThemeOptions =
        when(appThemeOptions){
            RepoThemeOptions.LIGHT -> DomainThemeOptions.LIGHT
            RepoThemeOptions.DARK -> DomainThemeOptions.DARK
            RepoThemeOptions.SYSTEM -> DomainThemeOptions.SYSTEM
        }

    fun toRepo(appThemeOptions: DomainThemeOptions): RepoThemeOptions =
        when(appThemeOptions){
            DomainThemeOptions.LIGHT -> RepoThemeOptions.LIGHT
            DomainThemeOptions.DARK -> RepoThemeOptions.DARK
            DomainThemeOptions.SYSTEM -> RepoThemeOptions.SYSTEM

        }
}