package com.thmanyah.thmanyah.search.data.repository

import com.thmanyah.thmanyah.search.data.remote.SearchApiService
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.thmanyah.thmanyah.core.model.DomainSection
import com.thmanyah.thmanyah.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: SearchApiService
) : SearchRepository {

    override fun search(query: String): Flow<PagingData<DomainSection>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(apiService, query) }
        ).flow
    }
}
