package com.composeit.preference.model

import androidx.annotation.StringRes
import com.composeit.preference.R

enum class AppThemeOptions(val id: Int, @StringRes val titleRes: Int) {

    LIGHT (0, R.string.preference_light_theme),

    DARK (1, R.string.preference_dark_theme),

    SYSTEM (2, R.string.preference_system_default_theme),

}