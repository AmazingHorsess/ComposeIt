package com.composeit.domain.usecase.preferences

import com.composeit.domain.model.AppThemeOptions
import kotlinx.coroutines.flow.Flow

class LoadAppTheme(
    private val preferencesRepository: PreferencesRepository
) {
    operator fun invoke(): Flow<AppThemeOptions> = preferencesRepository.loadAppTheme()
}