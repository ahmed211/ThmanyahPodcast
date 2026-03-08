package com.thmanyah.thmanyah

import com.thmanyah.thmanyah.search.domain.usecase.SearchUseCase
import com.thmanyah.thmanyah.search.presentation.SearchViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var searchUseCase: SearchUseCase
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        searchUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial query is empty`() = runTest {
        viewModel = SearchViewModel(searchUseCase)
        assertTrue(viewModel.query.value == "")
    }

    @Test
    fun `updating query correctly modifies state flow`() = runTest {
        viewModel = SearchViewModel(searchUseCase)

        viewModel.onQueryChanged("Art")
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(viewModel.query.value == "Art")
    }
}
