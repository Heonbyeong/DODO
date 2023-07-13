package com.example.dodo.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dodo.presentation.base.ScreenRoute
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
        composable(
            route = ScreenRoute.ADD.name + "/{selectedDate}/{isEdit}/{id}",
            arguments = listOf(
                navArgument("selectedDate") {
                    type = NavType.StringType
                },
                navArgument("isEdit") {
                    type = NavType.BoolType
                    defaultValue = false
                },
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            NavAnimation {
                TodoAddScreen(navController = navController)
            }
        }
    }
}