package com.thmanyah.thmanyah.core.ui.components

import com.thmanyah.thmanyah.core.model.DomainSection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.thmanyah.thmanyah.core.model.SectionType


@Composable
fun SectionView(section: DomainSection) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        
        Text(
            text = section.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))

        when (section.type) {
            SectionType.SQUARE -> SquareSection(section.content)
            SectionType.TWO_LINES_GRID -> TwoLinesGridSection(section.content)
            SectionType.BIG_SQUARE -> BigSquareSection(section.content)
            SectionType.QUEUE -> QueueSection(section.content)
            else -> SquareSection(section.content) 
        }
    }
}

