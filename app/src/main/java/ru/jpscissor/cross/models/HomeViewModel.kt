package ru.jpscissor.cross.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.jpscissor.cross.screens.home.HomeUiState

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun updateCalNorm(newNorm: Int) {
        _uiState.update { currentState ->
            currentState.copy(calNorm = newNorm)
        }
    }

    fun updateNutritionNorm(newProteins: Int, newCarbs: Int, newFats: Int) {
        _uiState.update { currentState ->
            currentState.copy(proteinsNorm = newProteins, carbsNorm = newCarbs, fatsNorm = newFats)
        }
    }

    fun updateNutrition(newProteins: Int, newCarbs: Int, newFats: Int) {
        _uiState.update { currentState ->
            currentState.copy(proteins = newProteins, carbs = newCarbs, fats = newFats)
        }
    }

}