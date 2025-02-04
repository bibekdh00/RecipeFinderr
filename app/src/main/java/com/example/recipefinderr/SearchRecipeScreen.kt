package com.example.recipefinderr

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchRecipeScreen(navController: NavController, viewModel: RecipeViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val recipes by viewModel.recipes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Search Recipe",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search by name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        val filteredRecipes = recipes.filter { recipe ->
            recipe.name.contains(searchQuery, ignoreCase = true)
        }

        if (filteredRecipes.isEmpty()) {
            Text(text = "No recipes found.")
        } else {
            filteredRecipes.forEach { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = {
                        // Navigate to AllRecipesScreen and pass the selected recipe
                        navController.navigate("allRecipes/${recipe.name}")
                    }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = recipe.name, style = MaterialTheme.typography.titleLarge)
                        Text(text = recipe.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Home")
        }
    }
}