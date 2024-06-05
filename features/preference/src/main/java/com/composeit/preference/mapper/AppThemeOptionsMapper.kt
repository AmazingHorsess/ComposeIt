package com.composeit.preference.mapper

import android.view.View
import com.composeit.preference.model.AppThemeOptions
import com.composeit.preference.model.AppThemeOptions as ViewAppThemeOptions
import com.composeit.domain.model.AppThemeOptions as DomainAppThemeOptions

internal class AppThemeOptionsMapper {

    fun toView(domainAppThemeOptions: DomainAppThemeOptions): ViewAppThemeOptions =
        when(domainAppThemeOptions){
            DomainAppThemeOptions.LIGHT -> ViewAppThemeOptions.LIGHT
            DomainAppThemeOptions.DARK -> ViewAppThemeOptions.DARK
            DomainAppThemeOptions.SYSTEM -> ViewAppThemeOptions.SYSTEM
        }

    fun toDomain(viewAppThemeOptions: AppThemeOptions): DomainAppThemeOptions =
        when(viewAppThemeOptions){
            ViewAppThemeOptions.LIGHT -> DomainAppThemeOptions.LIGHT
            ViewAppThemeOptions.DARK -> DomainAppThemeOptions.DARK
            ViewAppThemeOptions.SYSTEM -> DomainAppThemeOptions.SYSTEM
        }
}