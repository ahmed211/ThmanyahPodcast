package com.thmanyah.thmanyah.core.data.mapper

import com.thmanyah.thmanyah.core.model.DomainContentItem
import com.thmanyah.thmanyah.core.model.DomainSection
import com.thmanyah.thmanyah.core.data.dto.ContentItemDto
import com.thmanyah.thmanyah.core.data.dto.SectionDto

fun SectionDto.toDomain(): DomainSection {
    return DomainSection(
        name = this.name.orEmpty(),
        type = this.type.orEmpty(),
        contentType = this.contentType.orEmpty(),
        order = this.order?.toIntOrNull() ?: 0,
        content = this.content?.map { it.toDomain() } ?: emptyList()
    )
}

fun ContentItemDto.toDomain(): DomainContentItem {
    return DomainContentItem(
        podcastId = this.podcastId.orEmpty(),
        name = this.name.orEmpty(),
        description = this.description,
        avatarUrl = this.avatarUrl,
        episodeCount = this.episodeCount?.toIntOrNull(),
        duration = this.duration?.toLongOrNull(),
        language = this.language,
        priority = this.priority?.toIntOrNull(),
        popularityScore = this.popularityScore?.toDoubleOrNull(),
        score = this.score?.toDoubleOrNull()
    )
}
