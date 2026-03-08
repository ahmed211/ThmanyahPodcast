package com.thmanyah.thmanyah.home.presentation

import com.thmanyah.thmanyah.core.model.DomainSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thmanyah.thmanyah.home.domain.usecase.GetHomeSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HomeViewModel @Inject constructor(
    getHomeSectionsUseCase: GetHomeSectionsUseCase
) : ViewModel() {

    val homeSections: Flow<PagingData<DomainSection>> = getHomeSectionsUseCase()
        .cachedIn(viewModelScope)
}
