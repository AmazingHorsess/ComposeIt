package com.composeit.domain.usecase.fake

import com.composeit.domain.model.AppThemeOptions
import com.composeit.domain.usecase.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class PreferencesRepositoryFake: PreferencesRepository {
    private var theme:AppThemeOptions = AppThemeOptions.SYSTEM
    override suspend fun updateAppTheme(theme: AppThemeOptions) {
        this.theme = theme
    }

    override fun loadAppTheme(): Flow<AppThemeOptions> =
        flowOf(theme)

}