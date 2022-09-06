package com.aldikitta.formauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aldikitta.formauth.ui.theme.FormAuthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(
                            route = Screen.LoginScreen.route,
//                            arguments = listOf(
//                                navArgument("name"){
//                                    type = NavType.StringType
//                                    defaultValue = "Aldi"
//                                    nullable = true
//                                }
//                            )
                        ) {
                            LoginScreen(navController = navController)
                        }
                        composable(route = "dashboard_screen/{name}/{surname}/{email}/{phone}",
                            arguments = listOf(
                                navArgument(name = "name") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "surname") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "email") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "phone") {
                                    type = NavType.StringType
                                }
                            )) {
                            DashboardScreen(
                                navController = navController,
                                name = it.arguments?.getString("name")!!,
                                surname = it.arguments?.getString("surname")!!,
                                email = it.arguments?.getString("email")!!,
                                phoneNumber = it.arguments?.getString("phone")!!,
                            )
                        }
                    }
                }
            }
        }
    }
}

