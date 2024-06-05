package com.composeit.presentation

import androidx.lifecycle.ViewModel
import com.composeit.domain.usecase.preferences.LoadAppTheme
import com.composeit.presentation.mapper.AppThemeOptionsMapper
import com.composeit.presentation.model.AppThemeOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal class MainViewModel(
    private val loadAppTheme: LoadAppTheme,
    private val mapper: AppThemeOptionsMapper,
): ViewModel(){
    fun loadCurrentTheme(): Flow<AppThemeOptions> = loadAppTheme().map {mapper.toViewData(it)}
}