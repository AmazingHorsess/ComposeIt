package com.composeit.domain.repository

import com.composeit.domain.model.AppThemeOptions
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun updateAppTheme(theme: AppThemeOptions)

    fun loadAppTheme(): Flow<AppThemeOptions>
}