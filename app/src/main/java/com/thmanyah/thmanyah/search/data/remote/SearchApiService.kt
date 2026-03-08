package com.thmanyah.thmanyah.search.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.thmanyah.thmanyah.core.data.dto.SectionsResponseDto

interface SearchApiService {
    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): SectionsResponseDto
}
