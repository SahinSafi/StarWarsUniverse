package com.safi.domain.repository

import com.safi.common.base.ApiResult
import com.safi.entity.CharacterListItemEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchCharacters(page : Int) : Flow<ApiResult<List<CharacterListItemEntity>>>

}