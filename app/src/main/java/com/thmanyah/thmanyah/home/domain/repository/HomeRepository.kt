package com.thmanyah.thmanyah.home.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.thmanyah.thmanyah.core.model.DomainSection

interface HomeRepository {
    fun getHomeSections(): Flow<PagingData<DomainSection>>
}
