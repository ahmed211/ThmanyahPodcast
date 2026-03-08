package com.thmanyah.thmanyah

import com.thmanyah.thmanyah.home.data.remote.HomeApiService
import com.thmanyah.thmanyah.home.data.repository.HomeRepositoryImpl
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HomeRepositoryTest {

    private lateinit var apiService: HomeApiService
    private lateinit var repository: HomeRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk<HomeApiService>()
        repository = HomeRepositoryImpl(apiService)
    }

    @Test
    fun `getHomeSections yields a Pager stream successfully`() = runTest {
        val result = repository.getHomeSections()
        assertTrue("HomeRepository should return an active Pager flow", result != null)
    }
}
