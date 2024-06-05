package com.composeit.glance.presentation

import android.content.Context
import android.os.Build
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.color.ColorProviders
import androidx.glance.material3.ColorProviders
import com.composeit.design.ComposeItDarkColorScheme
import com.composeit.design.ComposeItLightColorScheme
import com.google.android.material.color.utilities.DynamicColor

@Composable
fun ComposeItGlanceTheme(
    context: Context = LocalContext.current,
    dynamicColors: Boolean = true,
    content: @Composable () -> Unit,
){
    val colorProvider = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && dynamicColors) {
        ColorProviders(
            light = dynamicLightColorScheme(context),
            dark = dynamicDarkColorScheme(context),

        )
    } else {
        ColorProviders(
            light = ComposeItLightColorScheme,
            dark = ComposeItDarkColorScheme
        )
    }
    GlanceTheme (colors = colorProvider, content = content)
}