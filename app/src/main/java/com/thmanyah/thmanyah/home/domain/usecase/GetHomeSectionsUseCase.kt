package com.thmanyah.thmanyah.home.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.thmanyah.thmanyah.core.model.DomainSection
import com.thmanyah.thmanyah.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeSectionsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<PagingData<DomainSection>> {
        return repository.getHomeSections()
    }
}
