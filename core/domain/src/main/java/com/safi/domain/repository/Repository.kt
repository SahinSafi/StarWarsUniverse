package com.safi.domain.repository

import com.safi.common.base.ApiResult
import com.safi.entity.CharacterListItemEntity
import com.safi.entity.PlanetListItemEntity
import com.safi.entity.StarshipListItemEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchCharacters(page : Int) : Flow<ApiResult<List<CharacterListItemEntity>>>

    suspend fun fetchStarships(page : Int) : Flow<ApiResult<List<StarshipListItemEntity>>>

    suspend fun fetchPlanets(page : Int) : Flow<ApiResult<List<PlanetListItemEntity>>>

}