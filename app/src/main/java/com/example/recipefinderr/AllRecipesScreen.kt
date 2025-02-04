package com.example.recipefinderr

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AllRecipesScreen(
    navController: NavController,
    viewModel: RecipeViewModel,
    selectedRecipeName: String? = null
) {
    val recipes by viewModel.recipes.collectAsState()
    val reviews by viewModel.reviews.collectAsState()

    ThemedScreen { // ✅ Wrapping everything inside ThemedScreen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "All Recipes",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground, // ✅ Uses theme text color
                modifier = Modifier.padding(bottom = 20.dp)
            )

            if (recipes.isEmpty()) {
                Text(text = "No recipes found.", color = MaterialTheme.colorScheme.onSurface)
            } else {
                recipes.forEach { recipe ->
                    val isSelected = recipe.name == selectedRecipeName
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = recipe.name,
                                style = MaterialTheme.typography.titleLarge,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = recipe.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                            )

                            // Favorite Button
                            Button(
                                onClick = { viewModel.toggleFavorite(recipe) },
                                modifier = Modifier.padding(top = 8.dp)
                            ) {
                                Text("❤️ Favorite")
                            }

                            // Display reviews
                            val recipeReviews = reviews.filter { it.recipe == recipe }
                            if (recipeReviews.isNotEmpty()) {
                                Text(
                                    text = "Reviews:",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                                recipeReviews.forEach { review ->
                                    Text(text = "⭐ ${review.rating}: ${review.text}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
