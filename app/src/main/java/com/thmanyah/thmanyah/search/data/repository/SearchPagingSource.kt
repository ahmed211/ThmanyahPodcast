package com.thmanyah.thmanyah.search.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thmanyah.thmanyah.core.data.mapper.toDomain
import com.thmanyah.thmanyah.search.data.remote.SearchApiService
import com.thmanyah.thmanyah.core.model.DomainSection
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val apiService: SearchApiService,
    private val query: String
) : PagingSource<Int, DomainSection>() {

    override fun getRefreshKey(state: PagingState<Int, DomainSection>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainSection> {
        return try {
            if (query.isBlank()) {
                return LoadResult.Page(emptyList(), null, null)
            }
            
            val page = params.key ?: 1
            val response = apiService.search(query = query, page = page)
            
            val sections = response.sections.map { it.toDomain() }.sortedBy { it.order }
            
            val totalPages = response.pagination?.totalPages ?: 1
            val nextPage = if (page < totalPages) page + 1 else null

            LoadResult.Page(
                data = sections,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
