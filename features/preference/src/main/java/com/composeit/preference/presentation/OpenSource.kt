package com.composeit.preference.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.composeit.design.components.ComposeItDialog
import com.composeit.design.components.ComposeItTopAppBar
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.LibraryDefaults

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OpenSource(onUpPress: () -> Unit, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { ComposeItTopAppBar(onPress = onUpPress) },
        content = { paddingValues -> OpenSourceContent(modifier = Modifier.padding(paddingValues)) },
        modifier = modifier,
    )
}

@Composable
private fun OpenSourceContent(modifier: Modifier = Modifier) {
    val dialog = remember {
        mutableStateOf(false)
    }
    LibrariesContainer(
        modifier = modifier.fillMaxSize(),
        colors = LibraryDefaults.libraryColors(
            backgroundColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            badgeContentColor = MaterialTheme.colorScheme.onPrimary,
            badgeBackgroundColor = MaterialTheme.colorScheme.primary,
        ),

    )
}