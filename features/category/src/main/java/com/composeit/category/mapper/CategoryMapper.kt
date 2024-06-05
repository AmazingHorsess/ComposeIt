package com.composeit.category.mapper

import android.graphics.Color
import com.amazinghorsess.category_api.model.Category
import com.libraries.core.extension.toStringColor
import com.amazinghorsess.category_api.model.Category as ViewCategory
import com.composeit.domain.model.Category as DomainCategory

internal class CategoryMapper {

    fun toView(domainCategoryList: List<DomainCategory>): List<ViewCategory> =
        domainCategoryList.map { toView(it) }

    fun toView(domainCategory: DomainCategory): ViewCategory =
        ViewCategory(
            id = domainCategory.id,
            name = domainCategory.name,
            color = Color.parseColor(domainCategory.color),
        )

    fun toDomain(viewCategoryList: List<ViewCategory>): List<DomainCategory> =
        viewCategoryList.map { toDomain(it) }

    fun toDomain(viewCategory: Category): DomainCategory =
        DomainCategory(
            id = viewCategory.id,
            name = viewCategory.name,
            color = viewCategory.color.toStringColor(),
        )
}