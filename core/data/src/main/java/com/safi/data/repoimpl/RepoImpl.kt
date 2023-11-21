package com.safi.data.repoimpl

import com.safi.common.base.ApiResult
import com.safi.data.NetworkBoundResource
import com.safi.data.apiservices.ApiService
import com.safi.data.mappers.CharacterApiMapper
import com.safi.data.mappers.mapResponseToEntity
import com.safi.domain.repository.Repository
import com.safi.entity.CharacterListItemEntity
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val networkBoundResource: NetworkBoundResource,
    private val apiService: ApiService,
    private val characterApiMapper: CharacterApiMapper
) : Repository {

    override suspend fun fetchCharacters(page: Int): Flow<ApiResult<List<CharacterListItemEntity>>> {
        Timber.e("ApiResponse RepoImpl")
        return mapResponseToEntity(
            response = networkBoundResource.downloadData {
                val result = apiService.fetchCharacters(page)
                Timber.e("ApiResponse $result")
                result
            },
            mapper = characterApiMapper
        )
    }

}