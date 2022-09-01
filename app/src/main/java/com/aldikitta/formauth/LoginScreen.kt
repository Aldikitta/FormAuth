package com.aldikitta.formauth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    onChange: (String) -> Unit = {},

    ) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var userName by remember {
        mutableStateOf("")
    }
    val storeUsername = userName

    var password by remember {
        mutableStateOf("")
    }
    val storePassword = password

    val minChar = 10
    val maxChar = 15
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login")
            TextField(
                value = userName,
                onValueChange = {
                    userName = it
                    onChange(it)
                },
                placeholder = {
                    Text(text = "Username")
                },
            )
            TextField(
                value = password,
                onValueChange = {
                    password = it
                    onChange(it)
                },
                placeholder = {
                    Text(text = "Password")
                },
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisibility)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(imageVector = Icons.Filled.Password, "")
                    }
                }
            )
            Text(text = "Username: $storeUsername")
            Text(text = "Password: $storePassword")
            Button(
                onClick = {
                    navController.navigate(Screen.DashboardScreen.route)
//                    navController.navigate(Screen.DashboardScreen.route + storeUsername)
//                    navController.navigate(Screen.DashboardScreen.route + storePassword)
                }
            ) {
                Text(text = "Login")
            }
        }

    }
}