package com.safi.starship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.safi.domain.usecase.FetchStarshipsUseCase
import com.safi.entity.StarshipListItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class StarshipViewModel @Inject constructor(
    private val fetchStarshipsUseCase: FetchStarshipsUseCase
): ViewModel() {

    private var _uiState = emptyFlow<PagingData<StarshipListItemEntity>>()
    val uiState get() = _uiState

    init { fetchCharacter() }

    private fun fetchCharacter() {
        _uiState = Pager(
            config = PagingConfig(pageSize = 10, maxSize = 100),
            pagingSourceFactory = { StarshipPagingSource(fetchStarshipsUseCase) }).flow.cachedIn(viewModelScope)

    }

}