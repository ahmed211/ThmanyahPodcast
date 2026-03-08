package com.thmanyah.thmanyah.search.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import com.thmanyah.thmanyah.core.model.DomainSection
import com.thmanyah.thmanyah.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    operator fun invoke(query: String): Flow<PagingData<DomainSection>> {
        if (query.isBlank()) return emptyFlow()
        return repository.search(query)
    }
}
