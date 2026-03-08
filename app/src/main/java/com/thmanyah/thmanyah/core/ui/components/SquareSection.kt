package com.thmanyah.thmanyah.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.thmanyah.thmanyah.core.model.DomainContentItem
import com.thmanyah.thmanyah.core.ui.theme.ThmanyahTheme

@Composable
fun SquareSection(items: List<DomainContentItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(items, key = { index, item -> "${item.podcastId}_$index" }) { _, item ->
            ContentCard(
                imageUrl = item.avatarUrl,
                title = item.name,
                subtitle = item.episodeCount?.let { "$it episodes" },
                imageSize = 140.dp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SquareSectionPreview() {
    ThmanyahTheme {
        SquareSection(items = MockData.sampleContentItems)
    }
}
