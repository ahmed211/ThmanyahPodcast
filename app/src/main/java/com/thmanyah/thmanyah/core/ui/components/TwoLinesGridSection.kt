package com.thmanyah.thmanyah.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.thmanyah.thmanyah.core.model.DomainContentItem
import com.thmanyah.thmanyah.core.ui.theme.ThmanyahTheme

@Composable
fun TwoLinesGridSection(items: List<DomainContentItem>) {
    val chunked = items.chunked(2)
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(chunked, key = { index, pair -> "${pair.firstOrNull()?.podcastId}_$index" }) { _, pair ->
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                pair.forEach { item ->
                    ContentCard(
                        imageUrl = item.avatarUrl,
                        title = item.name,
                        subtitle = item.language,
                        imageSize = 110.dp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TwoLinesGridSectionPreview() {
    ThmanyahTheme {
        TwoLinesGridSection(items = MockData.sampleContentItems)
    }
}
