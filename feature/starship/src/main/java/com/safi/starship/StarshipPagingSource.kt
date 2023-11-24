package com.safi.starship

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.safi.common.base.ApiResult
import com.safi.domain.usecase.FetchStarshipsUseCase
import com.safi.entity.StarshipListItemEntity

class StarshipPagingSource (
    private val fetchStarshipsUseCase: FetchStarshipsUseCase
): PagingSource<Int, StarshipListItemEntity>() {

    override fun getRefreshKey(state: PagingState<Int, StarshipListItemEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarshipListItemEntity> {
        val startPageIndex = 1
        val position = if (params.key == null || params.key == 0) startPageIndex else params.key
        var loadResult : LoadResult<Int, StarshipListItemEntity>? = null

        fetchStarshipsUseCase.execute(position!!).collect{ apiResult->
            loadResult = when(apiResult){

                is ApiResult.Error -> {
                    if (apiResult.code == 404)
                        LoadResult.Page(
                            data = emptyList(),
                            prevKey = if(position == startPageIndex) null else -1,
                            nextKey = null
                        )
                    else LoadResult.Error(Exception(apiResult.message))
                }
                is ApiResult.Loading -> LoadResult.Error(Exception(apiResult.loading.toString()))
                is ApiResult.Success -> {
                    LoadResult.Page(data = apiResult.data,
                        prevKey = if(position == startPageIndex) null else -1,
                        nextKey = if (apiResult.data.isEmpty()) null else position + 1)
                }
            }
        }

        return loadResult?:LoadResult.Error(Exception())
    }
}