package com.example.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CharacterUiState {
    object Loading : CharacterUiState()
    data class Success(val characters: List<Character>) : CharacterUiState()
    data class Error(val message: String) : CharacterUiState()
}

class CharacterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterUiState.Loading
            try {
                val cats = RetrofitClient.characterService.getCharacters()
                _uiState.value = CharacterUiState.Success(cats)
            } catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(e.localizedMessage ?: "Error al cargar")
            }
        }
    }
}