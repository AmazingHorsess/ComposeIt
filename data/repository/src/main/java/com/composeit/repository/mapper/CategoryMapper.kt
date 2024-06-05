package com.composeit.repository.mapper

import com.composeit.repository.model.Category as RepoCategory
import com.composeit.domain.model.Category as DomainCategory

internal class CategoryMapper {
    fun toDomain(repoCategory: RepoCategory): DomainCategory =
        DomainCategory(
            id = repoCategory.id,
            name = repoCategory.name,
            color = repoCategory.color
        )
    fun toDomain(repoCategoryList: List<RepoCategory>): List<DomainCategory> =
        repoCategoryList.map { toDomain(it) }

    fun toRepo(domainCategory: DomainCategory): RepoCategory =
        RepoCategory(
            id = domainCategory.id,
            name = domainCategory.name,
            color = domainCategory.color
        )

    fun toRepo(domainCategoryList: List<DomainCategory>): List<RepoCategory> =
        domainCategoryList.map { toRepo(it) }

}