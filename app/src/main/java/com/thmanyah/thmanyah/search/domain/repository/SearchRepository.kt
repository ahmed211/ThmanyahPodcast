package com.thmanyah.thmanyah.search.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.thmanyah.thmanyah.core.model.DomainSection

interface SearchRepository {
    fun search(query: String): Flow<PagingData<DomainSection>>
}
