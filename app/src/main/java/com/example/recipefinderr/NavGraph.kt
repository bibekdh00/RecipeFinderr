package com.example.recipefinderr

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipefinderr.*

@Composable
fun NavGraph(navController: NavHostController, viewModel: RecipeViewModel, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(navController) }
        composable("allRecipes") {
            AllRecipesScreen(navController = navController, viewModel = viewModel)
        }
        composable("allRecipes/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName")
            AllRecipesScreen(navController = navController, viewModel = viewModel, selectedRecipeName = recipeName)
        }
        composable("addRecipe") { AddRecipeScreen(navController = navController, viewModel = viewModel) }
        composable("deleteRecipe") { DeleteRecipeScreen(navController = navController, viewModel = viewModel) }
        composable("favorites") { FavoriteRecipesScreen(navController = navController, viewModel = viewModel) }
        composable("writeReview") { WriteReviewScreen(navController = navController, viewModel = viewModel) }
        composable("search") { SearchRecipeScreen(navController = navController, viewModel = viewModel) }
    }
}