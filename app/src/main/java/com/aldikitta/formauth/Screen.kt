package com.aldikitta.formauth

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login_screen")
    object DashboardScreen: Screen("dashboard_screen")
}