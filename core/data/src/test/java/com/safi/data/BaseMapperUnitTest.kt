package com.safi.data

import com.safi.common.base.ApiResult
import com.safi.data.mappers.CharacterApiMapper
import com.safi.data.mappers.mapResponseToEntity
import com.safi.entity.CharacterListItemEntity
import com.safi.response.CharactersApiResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BaseMapperUnitTest {

    @Test
    fun `api success response map to entity`() {

        val fakeApiResponse = flowOf<ApiResult<CharactersApiResponse>>(
            ApiResult.Success(
                CharactersApiResponse(
                    count = null,
                    next = null,
                    previous = null,
                    results = emptyList()
                )
            )
        )

        runBlocking {
            val result = mapResponseToEntity(fakeApiResponse,CharacterApiMapper()).first()
            val expectation = ApiResult.Success(emptyList<CharacterListItemEntity>())

            assertEquals(expectation, result)
        }

    }

    @Test
    fun `api error response map to entity`() {
        val fakeErrorResponse = flowOf<ApiResult.Error<CharactersApiResponse>>(ApiResult.Error(message = "Page not found", code = 404))

        runBlocking {
            val result = mapResponseToEntity(fakeErrorResponse, CharacterApiMapper()).first()
            val expectation = ApiResult.Error<CharacterListItemEntity>(message = "Page not found", code = 404)

            assertEquals(expectation, result)
        }
    }
}