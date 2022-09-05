package com.aldikitta.formauth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun DashboardScreen(
    navController: NavController,
    onChange: (String) -> Unit = {},
//    usernameFromLogin: String
) {
    var username by remember { mutableStateOf("") }

//    LaunchedEffect(key1 = Unit){
//        username = usernameFromLogin
//    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate(Screen.LoginScreen.route)
        }) {
            Text(text = username)
        }

    }
}