package com.composeit.presentation.mapper

import com.composeit.presentation.model.AppThemeOptions as ViewDataAppThemeOptions
import com.composeit.domain.model.AppThemeOptions as DomainAppThemeOptions

internal class AppThemeOptionsMapper {

    fun toViewData(appThemeOptions: DomainAppThemeOptions): ViewDataAppThemeOptions =
        when(appThemeOptions){
            DomainAppThemeOptions.DARK -> ViewDataAppThemeOptions.DARK
            DomainAppThemeOptions.LIGHT -> ViewDataAppThemeOptions.LIGHT
            DomainAppThemeOptions.SYSTEM -> ViewDataAppThemeOptions.SYSTEM
        }
}