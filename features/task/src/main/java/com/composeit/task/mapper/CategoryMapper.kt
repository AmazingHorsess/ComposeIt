package com.composeit.task.mapper

import android.graphics.Color
import com.amazinghorsess.category_api.model.Category as ViewCategory
import com.composeit.domain.model.Category as DomainCategory

internal class CategoryMapper {

    fun toView(domainCategory: DomainCategory): ViewCategory =
        ViewCategory(
            id = domainCategory.id,
            name = domainCategory.name,
            color = Color.parseColor(domainCategory.color)
        )
}