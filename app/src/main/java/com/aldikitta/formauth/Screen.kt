package com.aldikitta.formauth

sealed class Screen(val route: String, var arguments: String?) {
    object LoginScreen : Screen("login_screen", "?usernameFromLogin={usernameFromLogin}"){
        val fullRoute = route + arguments
    }
//    object DashboardScreen : Screen("dashboard_screen", "?emailFromSignIn={emailFromSignIn}"){
//        val fullRoute = route + arguments
//    }
    object DashboardScreen : Screen("dashboard_screen", null)
}