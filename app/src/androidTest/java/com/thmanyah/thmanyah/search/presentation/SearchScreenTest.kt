package com.thmanyah.thmanyah.search.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.LoadStates
import androidx.paging.LoadState
import com.thmanyah.thmanyah.core.model.DomainContentItem
import com.thmanyah.thmanyah.core.model.DomainSection
import com.thmanyah.thmanyah.core.model.SectionType
import com.thmanyah.thmanyah.core.model.ContentType
import com.thmanyah.thmanyah.core.ui.theme.ThmanyahTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchScreen_rendersEmptyStateInitially() {
        composeTestRule.setContent {
            ThmanyahTheme {
                val emptyPagingItems = flowOf(
                    PagingData.empty<DomainSection>(
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.NotLoading(endOfPaginationReached = true),
                            prepend = LoadState.NotLoading(endOfPaginationReached = true),
                            append = LoadState.NotLoading(endOfPaginationReached = true)
                        )
                    )
                ).collectAsLazyPagingItems()

                SearchScreenContent(
                    query = "",
                    onQueryChange = {},
                    sections = emptyPagingItems,
                    onNavigateBack = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Start typing to search...").assertIsDisplayed()
        
        Thread.sleep(5000)
    }

    @Test
    fun searchScreen_displaysMockSearchResults() {
        val mockResults = listOf(
            DomainSection(
                name = "Search Results",
                type = SectionType.QUEUE,
                contentType = ContentType.EPISODE,
                order = 1,
                content = listOf(
                    DomainContentItem(
                        podcastId = "202",
                        name = "Art",
                        description = "Art Desc",
                        avatarUrl = null,
                        episodeCount = null,
                        duration = 3600,
                        language = "AR",
                        priority = 1,
                        popularityScore = 5.0,
                        score = 10.0
                    )
                )
            )
        )

        var currentQuery = "MockSearch"

        composeTestRule.setContent {
            ThmanyahTheme {
                val lazyPagingItems = flowOf(
                    PagingData.from(
                        data = mockResults,
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.NotLoading(endOfPaginationReached = true),
                            prepend = LoadState.NotLoading(endOfPaginationReached = true),
                            append = LoadState.NotLoading(endOfPaginationReached = true)
                        )
                    )
                ).collectAsLazyPagingItems()

                SearchScreenContent(
                    query = currentQuery,
                    onQueryChange = { currentQuery = it },
                    sections = lazyPagingItems,
                    onNavigateBack = {}
                )
            }
        }

        composeTestRule.onNodeWithText("Art").assertIsDisplayed()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodes(androidx.compose.ui.test.hasText("Art")).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithText("Art").assertIsDisplayed()
        
        composeTestRule.onNodeWithText("1h 0m").assertIsDisplayed()
        
        Thread.sleep(5000)
    }
}
