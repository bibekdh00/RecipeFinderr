package com.example.recipefinderr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    // Recipes with name and description
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    // Favorited recipes
    private val _favoriteRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val favoriteRecipes: StateFlow<List<Recipe>> get() = _favoriteRecipes

    // Reviews for recipes
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> get() = _reviews

    // Add a new recipe
    fun addRecipe(name: String, description: String) {
        viewModelScope.launch {
            val newRecipe = Recipe(name, description)
            _recipes.value = _recipes.value + newRecipe
        }
    }

    // Delete a recipe
    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _recipes.value = _recipes.value - recipe
        }
    }

    // Toggle favorite status for a recipe
    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            if (_favoriteRecipes.value.contains(recipe)) {
                _favoriteRecipes.value = _favoriteRecipes.value - recipe
            } else {
                _favoriteRecipes.value = _favoriteRecipes.value + recipe
            }
        }
    }

    // Add a review for a recipe
    fun addReview(recipe: Recipe, reviewText: String, rating: Int) {
        viewModelScope.launch {
            val newReview = Review(recipe, reviewText, rating)
            _reviews.value = _reviews.value + newReview
        }
    }
}

// Data class for a recipe
data class Recipe(
    val name: String,
    val description: String
)

// Data class for a review
data class Review(
    val recipe: Recipe,
    val text: String,
    val rating: Int
)