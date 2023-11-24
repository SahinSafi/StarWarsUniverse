package com.safi.data.repoimpl

import com.safi.common.base.ApiResult
import com.safi.data.NetworkBoundResource
import com.safi.data.apiservices.ApiService
import com.safi.data.mappers.CharacterApiMapper
import com.safi.data.mappers.StarshipApiMapper
import com.safi.data.mappers.mapResponseToEntity
import com.safi.domain.repository.Repository
import com.safi.entity.CharacterListItemEntity
import com.safi.entity.StarshipListItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val apiService: ApiService,
    private val characterApiMapper: CharacterApiMapper,
    private val starshipApiMapper: StarshipApiMapper
) : Repository {

    override suspend fun fetchCharacters(page: Int): Flow<ApiResult<List<CharacterListItemEntity>>> {
        return mapResponseToEntity(
            response = networkBoundResource.downloadData {
                apiService.fetchCharacters(page)
            },
            mapper = characterApiMapper
        )
    }

    override suspend fun fetchStarships(page: Int): Flow<ApiResult<List<StarshipListItemEntity>>> {
        return mapResponseToEntity(
            response = networkBoundResource.downloadData {
                apiService.fetchStarships(page)
            },
            mapper = starshipApiMapper
        )
    }

}