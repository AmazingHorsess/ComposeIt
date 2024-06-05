package com.composeit.domain.usecase.preferences

import com.composeit.domain.model.AppThemeOptions

class UpdateAppTheme(
    private val preferencesRepository: PreferencesRepository
) {
    suspend operator fun invoke(theme: AppThemeOptions) = preferencesRepository.updateAppTheme(theme)
}