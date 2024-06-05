package com.composeit.repository

import com.composeit.domain.model.AppThemeOptions
import com.composeit.domain.usecase.preferences.PreferencesRepository
import com.composeit.repository.datasource.PreferencesDataSource
import com.composeit.repository.mapper.AppThemeOptionsMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

internal class PreferenceRepositoryImpl(
    private val dataSource: PreferencesDataSource,
    private val mapper: AppThemeOptionsMapper
) : PreferencesRepository{

    override suspend fun updateAppTheme(theme: AppThemeOptions) =
        dataSource.updateAppTheme(mapper.toRepo(theme))

    override fun loadAppTheme(): Flow<AppThemeOptions> =
        dataSource.loadAppTheme().map { mapper.toDomain(it) }

}