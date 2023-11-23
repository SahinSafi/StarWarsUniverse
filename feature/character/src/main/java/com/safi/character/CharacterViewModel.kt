package com.safi.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.safi.entity.CharacterListItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterPagingSource: CharacterPagingSource
): ViewModel() {

    private var _uiState = emptyFlow<PagingData<CharacterListItemEntity>>()
    val uiState get() = _uiState

    init { fetchCharacter() }

    private fun fetchCharacter() {
        _uiState = Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { characterPagingSource }).flow.cachedIn(viewModelScope)

    }

}