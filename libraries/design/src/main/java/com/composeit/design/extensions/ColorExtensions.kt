package com.composeit.design.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun String.toArgbColor(): Int =
    Color(removePrefix("#").toLong(16) or 0x00000000FF000000).toArgb()