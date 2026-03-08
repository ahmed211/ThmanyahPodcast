package com.thmanyah.thmanyah.feature.home.presentation

import com.thmanyah.thmanyah.home.domain.usecase.GetHomeSectionsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import com.thmanyah.thmanyah.home.presentation.HomeViewModel
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getHomeSectionsUseCase: GetHomeSectionsUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getHomeSectionsUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initializes successfully with mocked use case return`() = runTest {
        coEvery { getHomeSectionsUseCase() } returns emptyFlow()
        viewModel = HomeViewModel(getHomeSectionsUseCase)
        
        
        
        val flowAssigned = viewModel.homeSections != null
        assertTrue("ViewModel Flow should not be null", flowAssigned)
    }
}
