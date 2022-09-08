package com.exercise.nasapictures.ui

import com.exercise.nasapictures.model.NASAPicturesModel

sealed class Screen(val route:String){

    object MainScreen : Screen ("main_screen")
    object DetailScreen : Screen ("detail_screen")

    fun withArgs(vararg args : Int) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
