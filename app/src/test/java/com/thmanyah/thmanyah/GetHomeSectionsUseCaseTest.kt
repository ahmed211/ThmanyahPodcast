package com.thmanyah.thmanyah

import com.thmanyah.thmanyah.home.domain.repository.HomeRepository
import com.thmanyah.thmanyah.home.domain.usecase.GetHomeSectionsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetHomeSectionsUseCaseTest {

    private lateinit var repository: HomeRepository
    private lateinit var useCase: GetHomeSectionsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetHomeSectionsUseCase(repository)
    }

    @Test
    fun `invoke returns Flow of PagingData`() = runTest {
        coEvery { repository.getHomeSections() } returns emptyFlow()

        val result = useCase()
        assertTrue("Flow should be returned successfully", result != null)
    }
}
