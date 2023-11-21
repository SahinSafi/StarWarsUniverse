package com.safi.data.mappers

import com.safi.common.base.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Mapper<R,E>{
    fun mapFrom(type:R):E
}

fun <R,E> mapResponseToEntity(response : Flow<ApiResult<R>>, mapper : Mapper<R,E>) : Flow<ApiResult<E>> {
    return response.map {
        when(it) {
            is ApiResult.Success -> ApiResult.Success(mapper.mapFrom(it.data))
            is ApiResult.Error -> ApiResult.Error(it.message, it.code)
            is ApiResult.Loading -> ApiResult.Loading(it.loading)
        }
    }
}