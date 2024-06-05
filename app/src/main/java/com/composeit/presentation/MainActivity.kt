package com.composeit.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.composeit.design.ComposeItTheme
import com.composeit.navigation.NavGraph
import com.composeit.presentation.model.AppThemeOptions
import org.koin.androidx.viewmodel.ext.android.getViewModel

internal class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            val isDarkTheme = rememberIsDarkTheme()
            updateTheme(isDarkTheme)
            ComposeItTheme {
                NavGraph(windowSizeClass = calculateWindowSizeClass())
            }
        }
    }
    private fun updateTheme(darkTheme: Boolean) {
        window.apply {
            statusBarColor = if (darkTheme) Color.BLACK else Color.WHITE
            navigationBarColor = if (darkTheme) Color.BLACK else Color.WHITE
            WindowInsetsControllerCompat(this, this.decorView).isAppearanceLightStatusBars =
                !darkTheme
        }
    }
    @Composable
    private fun rememberIsDarkTheme(viewModel: MainViewModel = getViewModel()): Boolean {
        val isSystemDarkTheme = isSystemInDarkTheme()

        val theme by remember(viewModel) {
            viewModel.loadCurrentTheme()
        }.collectAsState(initial = AppThemeOptions.SYSTEM)

        val isDarkTheme = when (theme) {
            AppThemeOptions.LIGHT -> false
            AppThemeOptions.DARK -> true
            AppThemeOptions.SYSTEM -> isSystemDarkTheme
        }
        return isDarkTheme
    }
}



