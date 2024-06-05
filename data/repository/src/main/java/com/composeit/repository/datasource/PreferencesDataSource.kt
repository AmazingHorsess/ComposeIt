package com.composeit.repository.datasource

import com.composeit.repository.model.AppThemeOptions
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {

    suspend fun updateAppTheme(theme: AppThemeOptions)

    fun loadAppTheme(): Flow<AppThemeOptions>
}