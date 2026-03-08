package com.thmanyah.thmanyah.core.ui.components

import com.thmanyah.thmanyah.core.model.DomainContentItem

object MockData {
    val sampleContentItems = listOf(
        DomainContentItem(
            podcastId = "1",
            name = "ثمانية أسئلة",
            description = "أهم الأسئلة اليومية",
            avatarUrl = null,
            episodeCount = 120,
            duration = 3600,
            language = "Arabic",
            priority = 1,
            popularityScore = 5.0,
            score = 10.0
        ),
        DomainContentItem(
            podcastId = "2",
            name = "فنجان",
            description = "مع عبدالرحمن أبومالح",
            avatarUrl = null,
            episodeCount = 300,
            duration = 5400,
            language = "Arabic",
            priority = 2,
            popularityScore = 4.8,
            score = 9.8
        ),
        DomainContentItem(
            podcastId = "3",
            name = "سقراط",
            description = "قصص ملهمة",
            avatarUrl = null,
            episodeCount = 50,
            duration = 2400,
            language = "Arabic",
            priority = 3,
            popularityScore = 4.5,
            score = 9.5
        )
    )
}
