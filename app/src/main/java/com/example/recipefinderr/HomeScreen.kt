package com.example.recipefinderr

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Title
        Text(
            text = "Recipe Finder",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Navigation Buttons
        val menuItems = listOf(
            "All Recipes" to "allRecipes",
            "Add Recipe" to "addRecipe",
            "Delete Recipe" to "deleteRecipe",
            "Favorites" to "favorites",
            "Write Review" to "writeReview",
            "Search" to "search"
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(menuItems) { (title, destination) ->
                Button(
                    onClick = { navController.navigate(destination) },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text(text = title)
                }
            }
        }
    }
}