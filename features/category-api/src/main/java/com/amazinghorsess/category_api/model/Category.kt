package com.amazinghorsess.category_api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long = 0,
    val name: String,
    val color: Int,
) : Parcelable