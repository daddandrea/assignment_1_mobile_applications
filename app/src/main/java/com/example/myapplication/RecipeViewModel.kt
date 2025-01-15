package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel() {
    private val _recipes = MutableStateFlow(RecipeRepository.recipes)
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    fun queryRecipes(query: String) {
        if (query.length < 3) {
            _recipes.value = RecipeRepository.recipes
            return
        }

        val filteredRecipes = RecipeRepository.recipes.filter { recipe: Recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }

        _recipes.update { filteredRecipes }
    }
}