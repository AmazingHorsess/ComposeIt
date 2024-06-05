package com.composeitdatastore.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.composeit.repository.datasource.PreferencesDataSource
import com.composeit.repository.model.AppThemeOptions
import com.composeitdatastore.dataStore
import com.composeitdatastore.mapper.AppThemeOptionsMapper
import com.composeitdatastore.model.AppThemeOptions as DataStoreThemeOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PreferencesDataSourceImpl(
    private val context: Context,
    private val mapper: AppThemeOptionsMapper
): PreferencesDataSource {
    override suspend fun updateAppTheme(theme: AppThemeOptions) {
        context.dataStore.edit { settings ->
            settings[APP_THEME_OPTION] = mapper.toDataStore(theme).id
        }
    }

    override fun loadAppTheme(): Flow<AppThemeOptions> =
        context.dataStore.data.map { preferences ->
            val id = preferences[APP_THEME_OPTION] ?: DataStoreThemeOptions.SYSTEM
            val result = DataStoreThemeOptions.entries.find { it.id == id } ?: DataStoreThemeOptions.SYSTEM
            mapper.toRepo(result)
        }


    private companion object{
        val APP_THEME_OPTION = intPreferencesKey("composeit_theme_option")
    }
}