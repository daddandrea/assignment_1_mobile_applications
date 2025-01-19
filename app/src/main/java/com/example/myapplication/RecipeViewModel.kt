package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RecipeUIState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList()
)

class RecipeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUIState())
    val uiState: StateFlow<RecipeUIState> = _uiState.asStateFlow()

    private var searchJob: kotlinx.coroutines.Job? = null

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(
                isLoading = false,
                recipes = RecipeRepository.recipes
            ) }
        }
    }

    fun queryRecipes(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, recipes = emptyList()) }

            delay(300)

            val filteredRecipes = if (query.length < 3) {
                RecipeRepository.recipes
            } else {
                RecipeRepository.recipes.filter { recipe ->
                    recipe.name.contains(query, ignoreCase = true)
                }
            }

            delay(2000)

            _uiState.update { it.copy(isLoading = false, recipes = filteredRecipes) }
        }
    }

    fun logout() {
        MyApplication.getInstance().credentialsManager.logout()
    }
}