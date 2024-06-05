package com.composeit.preference.presentation

import androidx.lifecycle.ViewModel
import com.composeit.domain.usecase.preferences.LoadAppTheme
import com.composeit.domain.usecase.preferences.UpdateAppTheme
import com.composeit.preference.mapper.AppThemeOptionsMapper
import com.composeit.preference.model.AppThemeOptions
import com.libraries.core.coroutines.AppCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PreferenceViewModel(
    private val updateThemeUseCase: UpdateAppTheme,
    private val loadAppTheme: LoadAppTheme,
    private val applicationScope: AppCoroutineScope,
    private val themeMapper: AppThemeOptionsMapper,
): ViewModel() {

    fun loadCurrentTheme(): Flow<AppThemeOptions> = loadAppTheme().map { themeMapper.toView(it) }

    fun updateTheme(theme: AppThemeOptions) =
        applicationScope.launch {
            val updatedTheme = themeMapper.toDomain(theme)
            updateThemeUseCase(updatedTheme)
        }

    }
