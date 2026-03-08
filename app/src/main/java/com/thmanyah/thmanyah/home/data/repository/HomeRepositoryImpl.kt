package com.thmanyah.thmanyah.home.data.repository

import com.thmanyah.thmanyah.home.data.remote.HomeApiService
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.thmanyah.thmanyah.core.model.DomainSection
import com.thmanyah.thmanyah.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: HomeApiService
) : HomeRepository {

    override fun getHomeSections(): Flow<PagingData<DomainSection>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { HomePagingSource(apiService) }
        ).flow
    }
}
