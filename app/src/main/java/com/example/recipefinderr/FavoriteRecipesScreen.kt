package com.example.recipefinderr

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FavoriteRecipesScreen(navController: NavController, viewModel: RecipeViewModel) {
    val favoriteRecipes by viewModel.favoriteRecipes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favorite Recipes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        if (favoriteRecipes.isEmpty()) {
            Text(text = "No favorite recipes found.")
        } else {
            favoriteRecipes.forEach { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = recipe.name, style = MaterialTheme.typography.titleLarge)
                        Text(text = recipe.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}