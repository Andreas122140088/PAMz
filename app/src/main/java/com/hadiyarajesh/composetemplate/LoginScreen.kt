package com.example.myapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hadiyarajesh.composetemplate.R
import kotlinx.coroutines.delay

data class LoginState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@Composable
fun LoginScreen(
    onLoginSuccess: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginState by remember { mutableStateOf(LoginState()) }

    // Simulasi proses login
    LaunchedEffect(loginState.isLoading) {
        if (loginState.isLoading) {
            delay(1000) // Simulasi delay login
            loginState = loginState.copy(
                isLoading = false,
                errorMessage = if (email.isEmpty() || password.isEmpty()) {
                    "Email and password cannot be empty"
                } else {
                    onLoginSuccess(email, password)
                    null
                }
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE3F2FD))
                    .padding(padding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.erh),
                    contentDescription = "Deskripsi aksesibilitas",
                    modifier = Modifier.size(200.dp)
                )

                Text(
                    text = "Welcome ERH",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = loginState.errorMessage != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    enabled = !loginState.isLoading
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = loginState.errorMessage != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    enabled = !loginState.isLoading
                )

                loginState.errorMessage?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }

                Button(
                    onClick = {
                        loginState = loginState.copy(isLoading = true)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = !loginState.isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50) // Change to green
                    )
                ) {
                    if (loginState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Login")
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onLoginSuccess = { email, password -> }
        )
    }
}