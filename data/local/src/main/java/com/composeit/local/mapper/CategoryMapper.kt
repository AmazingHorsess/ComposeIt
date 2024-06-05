package com.composeit.local.mapper

import com.composeit.repository.model.Category
import com.composeit.local.model.Category as LocalCategory
import com.composeit.repository.model.Category as RepoCategory

internal class CategoryMapper {

    fun toLocal(repoCategory: RepoCategory): LocalCategory =
        LocalCategory(
            categoryId = repoCategory.id,
            name = repoCategory.name,
            categoryColor = repoCategory.color,
        )

    fun toLocal(repoCategoryList: List<RepoCategory>): List<LocalCategory> =
        repoCategoryList.map { toLocal(it) }

    fun toRepo(localCategory: LocalCategory): RepoCategory =
        RepoCategory(
            id = localCategory.categoryId,
            name = localCategory.name,
            color = localCategory.categoryColor,
        )

    fun toRepo(localCategoryList: List<LocalCategory>):List<RepoCategory> =
        localCategoryList.map { toRepo(it) }
}