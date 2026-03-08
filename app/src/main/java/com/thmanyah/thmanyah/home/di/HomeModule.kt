package com.thmanyah.thmanyah.home.di

import com.thmanyah.thmanyah.home.data.remote.HomeApiService
import com.thmanyah.thmanyah.home.data.repository.HomeRepositoryImpl
import com.thmanyah.thmanyah.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApiService(@Named("home") retrofit: Retrofit): HomeApiService {
        return retrofit.create(HomeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository = impl
}
