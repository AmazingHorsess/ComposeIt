package com.libraries.core.extension

fun Int.toStringColor() =
    String.format(HexFormat, HexWhite and this)

private const val HexFormat = "#%06X"

private const val HexWhite = 0xFFFFFF