package com.thmanyah.thmanyah.core.model

data class DomainSection(
    val name: String,
    val type: String,
    val contentType: String,
    val order: Int,
    val content: List<DomainContentItem>
)

data class DomainContentItem(
    val podcastId: String,
    val name: String,
    val description: String?,
    val avatarUrl: String?,
    val episodeCount: Int?,
    val duration: Long?,
    val language: String?,
    val priority: Int?,
    val popularityScore: Number?,
    val score: Number?
)


object SectionType {
    const val SQUARE = "square"
    const val TWO_LINES_GRID = "2_lines_grid"
    const val BIG_SQUARE = "big_square"
    const val QUEUE = "queue"
}


object ContentType {
    const val PODCAST = "podcast"
    const val EPISODE = "episode"
    const val AUDIOBOOK = "audiobook"
    const val ARTICLE = "article"
}
