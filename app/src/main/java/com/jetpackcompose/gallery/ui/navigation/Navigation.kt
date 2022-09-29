package com.jetpackcompose.gallery.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpackcompose.gallery.SharedViewModel
import com.jetpackcompose.gallery.ui.Screen
import com.jetpackcompose.gallery.ui.screen.DetailScreen
import com.jetpackcompose.gallery.ui.screen.MainContent


@Composable
fun Navigation(){

    val navController = rememberNavController()
    val sharedViewModel : SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainContent(navController = navController,sharedViewModel=sharedViewModel)
        }

        composable(route = Screen.DetailScreen.route + "/{position}",
            arguments = listOf(
                navArgument("position") {
                    type = NavType.IntType
                    defaultValue= 0
                    nullable = false
                }
            )) { entry ->
            entry.arguments?.getInt("position")?.let {
                DetailScreen(position = it, navController = navController,sharedViewModel=sharedViewModel)
            }

        }
    }

}