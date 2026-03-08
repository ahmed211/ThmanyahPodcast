package com.thmanyah.thmanyah.home.presentation

import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysMockDataCorrectly() {
        val mockSections = listOf(
            DomainSection(
                name = "Latest Podcasts",
                type = SectionType.SQUARE,
                contentType = ContentType.PODCAST,
                order = 1,
                content = listOf(
                    DomainContentItem(
                        podcastId = "101",
                        name = "Art Podcast",
                        description = "Art Description",
                        avatarUrl = null,
                        episodeCount = 5,
                        duration = 1000,
                        language = "AR",
                        priority = 1,
                        popularityScore = 5.0,
                        score = 10.0
                    )
                )
            )
        )

        composeTestRule.setContent {
            ThmanyahTheme {
                val lazyPagingItems = flowOf(
                    PagingData.from(
                        data = mockSections,
                        sourceLoadStates = LoadStates(
                            refresh = LoadState.NotLoading(endOfPaginationReached = true),
                            prepend = LoadState.NotLoading(endOfPaginationReached = true),
                            append = LoadState.NotLoading(endOfPaginationReached = true)
                        )
                    )
                ).collectAsLazyPagingItems()

                HomeScreenContent(
                    sections = lazyPagingItems,
                    onNavigateToSearch = {}
                )
            }
        }


        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodes(androidx.compose.ui.test.hasText("Latest Podcasts")).fetchSemanticsNodes().isNotEmpty()
        }


        composeTestRule.onNodeWithText("Latest Podcasts").assertIsDisplayed()
        

        composeTestRule.onNodeWithText("Art Podcast").assertIsDisplayed()
        

        composeTestRule.onNodeWithText("5 episodes").assertIsDisplayed()
        
        Thread.sleep(5000)
    }
}
