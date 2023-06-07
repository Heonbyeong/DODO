package com.example.dodo.presentation.main

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dodo.presentation.Screen
import com.example.dodo.ui.theme.gray0
import com.example.dodo.ui.theme.gray07
import com.example.dodo.ui.theme.gray09

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.NotificationScreen,
        Screen.SettingScreen
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = gray09,
        contentColor = gray07
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(id = item.title), fontSize = 12.sp) },
                selectedContentColor = gray0,
                unselectedContentColor = gray07,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            {
                                popUpTo(route = route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}