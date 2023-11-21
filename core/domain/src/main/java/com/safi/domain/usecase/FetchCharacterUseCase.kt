package com.safi.domain.usecase

import com.safi.domain.repository.Repository
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(page : Int) = repository.fetchCharacters(page)
}