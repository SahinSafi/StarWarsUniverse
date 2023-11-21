package com.safi.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.safi.domain.usecase.FetchCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val fetchCharacterUseCase : FetchCharacterUseCase
): ViewModel() {

    fun fetchCharacter() {
        viewModelScope.launch {
            fetchCharacterUseCase.execute(1).collect{
                Timber.e("ApiResponse $it")
            }
        }
    }
}