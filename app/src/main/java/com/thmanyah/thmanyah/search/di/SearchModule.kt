package com.thmanyah.thmanyah.search.di

import com.thmanyah.thmanyah.search.data.remote.SearchApiService
import com.thmanyah.thmanyah.search.data.repository.SearchRepositoryImpl
import com.thmanyah.thmanyah.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchApiService(@Named("search") retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(impl: SearchRepositoryImpl): SearchRepository = impl
}
