package com.composeit.category.presentation.bottomsheet.state

import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.amazinghorsess.category_api.model.Category
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
internal class CategoryBottomSheetState(
    private val category: Category,
): Parcelable {

    @IgnoredOnParcel
    var id by mutableStateOf(category.id)

    @IgnoredOnParcel
    var name by mutableStateOf(category.name)

    @IgnoredOnParcel
    var color by mutableStateOf(category.color)

    fun isEditing(): Boolean =
        id > 0L

    fun toCategory():Category=
        Category(
            id = id,
            name = name,
            color = color
        )

}