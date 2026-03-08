package com.thmanyah.thmanyah.core.data.dto

import com.google.gson.annotations.SerializedName

data class SectionsResponseDto(
    @SerializedName("sections") val sections: List<SectionDto>,
    @SerializedName("pagination") val pagination: PaginationDto?
)

data class PaginationDto(
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("total_pages") val totalPages: Int?
)

data class SectionDto(
    @SerializedName("name") val name: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("content_type") val contentType: String?,
    @SerializedName("order") val order: String?,
    @SerializedName("content") val content: List<ContentItemDto>?
)

data class ContentItemDto(
    @SerializedName("podcast_id") val podcastId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("episode_count") val episodeCount: String?,
    @SerializedName("duration") val duration: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("priority") val priority: String?,
    @SerializedName("popularityScore") val popularityScore: String?,
    @SerializedName("score") val score: String?
)
