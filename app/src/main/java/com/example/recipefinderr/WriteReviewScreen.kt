package com.example.recipefinderr

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WriteReviewScreen(navController: NavController, viewModel: RecipeViewModel) {
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }

    val recipes by viewModel.recipes.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Write a Review",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Dropdown to select a recipe
        if (selectedRecipe == null) {
            Text(text = "Select a recipe to review:")
            recipes.forEach { recipe ->
                Button(
                    onClick = { selectedRecipe = recipe },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(text = recipe.name)
                }
            }
        } else {
            Text(text = "Reviewing: ${selectedRecipe!!.name}")

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                label = { Text("Your review") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Rating: $rating")
            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 0f..5f,
                steps = 5
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (reviewText.isNotBlank() && selectedRecipe != null) {
                        viewModel.addReview(selectedRecipe!!, reviewText, rating)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Review")
            }
        }
    }
}