package com.thmanyah.thmanyah.home.data.remote

import retrofit2.http.GET
import com.thmanyah.thmanyah.core.data.dto.SectionsResponseDto
import retrofit2.http.Query

interface HomeApiService {
    @GET("home_sections")
    suspend fun getHomeSections(@Query("page") page: Int = 1): SectionsResponseDto
}
