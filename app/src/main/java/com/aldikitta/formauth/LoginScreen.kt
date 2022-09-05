package com.aldikitta.formauth

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.aldikitta.formauth.composable.CustomTextfield
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    onChange: (String) -> Unit = {},
) {
    ShowForm()
}

@Composable
fun ShowForm() {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var name by rememberSaveable { mutableStateOf("") }
    var surName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateSurname by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var validateIsPasswrodIsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = "Please, Input a valid name"
    val validateSurnameError = "Please, Input a valid surname"
    val validateEmailError = "Formal of the email doesn't seem right"
    val validatePhoneError = "Formal of the phone number doesn't seem right"
    val validatePasswordError =
        "Must mix capital and non-capital letters, a number, special character and a minimum length of 8"
    val validateIsPasswordIsEqualError = "Password must be equal"

    fun validateData(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).{8,}".toRegex()

        validateName = name.isNotBlank()
        validateSurname = surname.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validatePhone = Patterns.PHONE.matcher(phone).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = passwordRegex.matches(confirmPassword)
        validateIsPasswrodIsEqual = password == confirmPassword

        return validateName && validateSurname && validateEmail && validatePhone && validatePassword && validateConfirmPassword && validateIsPasswrodIsEqual
    }

    fun register(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ) {
        if (validateData(name, surname, email, phone, password, confirmPassword)) {
            Log.d(
                "TAG",
                "THIS IS FROM BUTTON $name $surname $email $phone $password $confirmPassword"
            )
        } else {
            Toast.makeText(context, "Please enter correct field", Toast.LENGTH_SHORT).show()
        }
    }

    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Register to our app",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = if (currentHour in 0..11) {
                "Good Morning"
            } else if (currentHour in 12..15) {
                "Good Afternoon"
            } else if (currentHour in 16..20) {
                "Good Evening"
            } else if (currentHour in 21..23) {
                "Good Night"
            } else {
                ""
            }
        )

        CustomTextfield(
            value = name,
            onValueChanged = { name = it },
            leadingIcon = Icons.Filled.PermIdentity,
            label = "Name",
            showError = !validateName,
            errorMessage = validateNameError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        CustomTextfield(
            value = surName,
            onValueChanged = { surName = it },
            leadingIcon = Icons.Filled.PermIdentity,
            label = "Surname",
            showError = !validateSurname,
            errorMessage = validateSurnameError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        CustomTextfield(
            value = email,
            onValueChanged = { email = it },
            leadingIcon = Icons.Filled.Email,
            label = "Email",
            showError = !validateEmail,
            errorMessage = validateEmailError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        CustomTextfield(
            value = phone,
            onValueChanged = { phone = it },
            leadingIcon = Icons.Filled.Phone,
            label = "Phone Number",
            showError = !validatePhone,
            errorMessage = validatePhoneError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        CustomTextfield(
            value = password,
            onValueChanged = { password = it },
            leadingIcon = Icons.Filled.Password,
            label = "Password",
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            showError = !validatePassword,
            onVisibilityChange = { isPasswordVisible = it },
            errorMessage = validatePasswordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        CustomTextfield(
            value = confirmPassword,
            onValueChanged = { confirmPassword = it },
            leadingIcon = Icons.Filled.Password,
            label = "Confirm Password",
            isPasswordField = true,
            isPasswordVisible = isConfirmPasswordVisible,
            showError = !validateConfirmPassword || !validateIsPasswrodIsEqual,
            onVisibilityChange = { isConfirmPasswordVisible = it },
            errorMessage = if (!validateConfirmPassword) validatePasswordError else validateIsPasswordIsEqualError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.clearFocus() }
            )
        )
        Button(onClick = {
            register(name, surName, email, phone, password, confirmPassword)
        }) {
            Text(text = "Take me in")
        }
    }
}