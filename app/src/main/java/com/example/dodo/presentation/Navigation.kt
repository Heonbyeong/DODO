package com.example.dodo.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dodo.presentation.home.HomeScreen
import com.example.dodo.presentation.notification.NotificationScreen
import com.example.dodo.presentation.setting.SettingScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = Screen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(route = Screen.SettingScreen.route) {
            SettingScreen()
        }
    }
}