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
import java.util.*

@Composable
fun DashboardScreen(
    navController: NavController,
    name: String,
    surname: String,
    email: String,
    phoneNumber: String
) {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (currentHour in 0..11) {
                "Good Morning $name"
            } else if (currentHour in 12..15) {
                "Good Afternoon $name"
            } else if (currentHour in 16..20) {
                "Good Evening $name"
            } else if (currentHour in 21..23) {
                "Good Night $name"
            } else {
                ""
            }
        )
        Text(text = "Here is your details")
        Text(text = "Surname: $surname")
        Text(text = "Email: $email")
        Text(text = "Phone Number: $phoneNumber")
    }
}