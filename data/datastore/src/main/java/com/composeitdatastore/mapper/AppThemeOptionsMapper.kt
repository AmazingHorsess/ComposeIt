package com.composeitdatastore.mapper

import com.composeit.repository.model.AppThemeOptions as RepoThemeOptions
import com.composeitdatastore.model.AppThemeOptions as DataStoreThemeOptions

internal class AppThemeOptionsMapper {

    fun toDataStore(appThemeOptions: RepoThemeOptions): DataStoreThemeOptions =
        when(appThemeOptions){
            RepoThemeOptions.LIGHT -> DataStoreThemeOptions.LIGHT
            RepoThemeOptions.DARK -> DataStoreThemeOptions.DARK
            RepoThemeOptions.SYSTEM -> DataStoreThemeOptions.SYSTEM
        }

    fun toRepo(appThemeOptions: DataStoreThemeOptions): RepoThemeOptions =
        when (appThemeOptions) {
            DataStoreThemeOptions.LIGHT -> RepoThemeOptions.LIGHT
            DataStoreThemeOptions.DARK -> RepoThemeOptions.DARK
            DataStoreThemeOptions.SYSTEM -> RepoThemeOptions.SYSTEM
        }
}