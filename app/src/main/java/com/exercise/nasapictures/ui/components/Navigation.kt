package com.exercise.nasapictures.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.exercise.nasapictures.ui.Screen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainContent(navController = navController)
        }
        composable(route = Screen.DetailScreen.route+ "/{name}",
                   arguments = listOf(
                       navArgument("name"){
                           type= NavType.StringType
                           defaultValue= "Prasannakumar"
                           nullable = true
                       }
                   ) ){ entry ->
            entry.arguments?.getString("name")?.let {
                DetailScreen(name = it,navController = navController) }
        }
    }

}