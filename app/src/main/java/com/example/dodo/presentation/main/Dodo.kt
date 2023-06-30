package com.example.dodo.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dodo.presentation.ScreenRoute
import com.example.dodo.presentation.common.NavAnimation
import com.example.dodo.presentation.todoadd.TodoAddScreen

@Composable
fun Dodo(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.MAIN.name
    ) {
        composable(ScreenRoute.MAIN.name) {
            NavAnimation {
                MainScreen(navController = navController)
            }
        }
        composable(ScreenRoute.ADD.name) {
            NavAnimation {
                TodoAddScreen(navController = navController)
            }
        }
    }
}