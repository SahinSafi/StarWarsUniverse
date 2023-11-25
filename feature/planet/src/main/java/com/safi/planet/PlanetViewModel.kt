package com.safi.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.safi.domain.usecase.FetchPlanetsUseCase
import com.safi.entity.PlanetListItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val fetchPlanetsUseCase: FetchPlanetsUseCase
): ViewModel() {

    private var _uiState = emptyFlow<PagingData<PlanetListItemEntity>>()
    val uiState get() = _uiState

    init { fetchCharacter() }

    private fun fetchCharacter() {
        _uiState = Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100),
            pagingSourceFactory = { PlanetPagingSource(fetchPlanetsUseCase) }).flow.cachedIn(viewModelScope)

    }

}