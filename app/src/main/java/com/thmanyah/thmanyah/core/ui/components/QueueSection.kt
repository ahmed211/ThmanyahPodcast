package com.thmanyah.thmanyah.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.thmanyah.thmanyah.core.model.DomainContentItem
import com.thmanyah.thmanyah.core.ui.theme.ThmanyahTheme

@Composable
fun QueueSection(items: List<DomainContentItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(items, key = { index, item -> "${item.podcastId}_$index" }) { _, item ->
            Column(modifier = Modifier.width(260.dp)) {
                QueueContentRow(
                    imageUrl = item.avatarUrl,
                    title = item.name,
                    subtitle = item.duration?.let { formatDuration(it) }
                )
            }
        }
    }
}

private fun formatDuration(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    return if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m"
}

@Preview(showBackground = true)
@Composable
fun QueueSectionPreview() {
    ThmanyahTheme {
        QueueSection(items = MockData.sampleContentItems)
    }
}
