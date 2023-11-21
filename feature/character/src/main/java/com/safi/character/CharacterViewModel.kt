package com.safi.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safi.common.base.ApiResult
import com.safi.domain.usecase.FetchCharacterUseCase
import com.safi.entity.CharacterListItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val fetchCharacterUseCase : FetchCharacterUseCase
): ViewModel() {

    val action: (UiAction) -> Unit = { handleUiAction(it) }

    private val _uiState = MutableStateFlow<UiState<Any>>(UiState.Loading(false))
    val uiState get() = _uiState

    init { fetchCharacter() }

    private fun handleUiAction(action: UiAction) {
        if (action is UiAction.RefreshApi) fetchCharacter()
    }

    private fun fetchCharacter() {
        viewModelScope.launch {
            fetchCharacterUseCase.execute(1).collect{ apiResult ->
                when(apiResult) {
                    is ApiResult.Loading -> _uiState.value = UiState.Loading(apiResult.loading)
                    is ApiResult.Success -> _uiState.value = UiState.CharacterList(apiResult.data)
                    is ApiResult.Error -> _uiState.value = UiState.ApiError(apiResult.message)
                }
            }
        }
    }

}

sealed class UiState<out R>{
    data class CharacterList(val data: List<CharacterListItemEntity>): UiState<CharacterList>()
    data class ApiError(val message : String): UiState<ApiError>()
    data class Loading(val isLoading : Boolean): UiState<Loading>()
}

sealed class UiAction {
    data object RefreshApi : UiAction()
}