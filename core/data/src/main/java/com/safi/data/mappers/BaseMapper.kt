package com.safi.data.mappers

import com.safi.common.base.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
/** mapper interface implement in api mapper class
 * -type [R] is Response
 * -type [E] is Entity */
interface Mapper<R,E>{

    /** method mapFrom receive response data and convert it to entity
     * -required @param [type] provide data for map to entity */
    fun mapFrom(type:R):E
}

/** map api response flow to entity flow
 * -required @param [response] to provide data
 * -required @param [mapper] to know how to format response data to entity data */
fun <R,E> mapResponseToEntity(response : Flow<ApiResult<R>>, mapper : Mapper<R,E>) : Flow<ApiResult<E>> {
    return response.map {
        when(it) {
            is ApiResult.Success -> ApiResult.Success(mapper.mapFrom(it.data))
            is ApiResult.Error -> ApiResult.Error(it.message, it.code)
            is ApiResult.Loading -> ApiResult.Loading(it.loading)
        }
    }
}