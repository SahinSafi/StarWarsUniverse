package com.safi.domain.usecase

import com.safi.domain.repository.Repository
import javax.inject.Inject

class FetchStarshipsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun execute(page : Int) = repository.fetchStarships(page)
}