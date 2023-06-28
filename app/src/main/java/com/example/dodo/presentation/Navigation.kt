package com.example.dodo.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dodo.presentation.home.HomeTodoScreen
import com.example.dodo.presentation.notification.NotificationScreen
import com.example.dodo.presentation.setting.SettingScreen

@Composable
fun Navigation(
    navHostController: NavHostController,
    navController: NavController
) {
    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeTodoScreen(navController = navController)
        }
        composable(route = Screen.Notification.route) {
            NotificationScreen()
        }
        composable(route = Screen.SettingScreen.route) {
            SettingScreen()
        }
    }
}