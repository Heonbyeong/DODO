package com.example.dodo.presentation.base

import com.example.dodo.R

sealed class Screen(val title: Int, val icon: Int, val route: String) {
    object HomeScreen : Screen(
        title = R.string.bottom_nav_home,
        icon = R.drawable.ic_bottom_home,
        route = ScreenRoute.HOME.name
    )

    object Notification : Screen(
        title = R.string.bottom_nav_add,
        icon = R.drawable.ic_bottom_add,
        route = ScreenRoute.NOTIFICATION.name
    )

    object SettingScreen : Screen(
        title = R.string.bottom_nav_setting,
        icon = R.drawable.ic_bottom_setting,
        route = ScreenRoute.SETTING.name
    )
}