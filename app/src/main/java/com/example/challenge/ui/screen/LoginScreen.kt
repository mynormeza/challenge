package com.example.challenge.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.challenge.presentation.uistate.LoginUIState

@Composable
fun LoginScreen(loginUIState: LoginUIState) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = loginUIState.result) {
        if (loginUIState.result.isNotEmpty()) {
            snackBarHostState.showSnackbar(
                message = loginUIState.result,
                duration = SnackbarDuration.Long,
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) {
        LoginScreenContent(it, loginUIState)
    }
}

@Composable
@Preview(showBackground = true)
private fun LoginScreenContent(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    loginUIState: LoginUIState = LoginUIState(),
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues),
        contentAlignment = Alignment.Center,
    ) {
        LoginForm(loginUIState)
    }
}

@Composable
@Preview(showBackground = true)
fun LoginForm(loginUIState: LoginUIState = LoginUIState()) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        OutlinedTextField(
            enabled = !loginUIState.isLoading,
            value = loginUIState.username,
            onValueChange = loginUIState.onChangeUsername,
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().testTag("UsernameTextField"),
        )
        AnimatedVisibility(visible = loginUIState.errorUsername.isNotEmpty()) {
            Text(
                text = loginUIState.errorUsername,
                color = Color.Red,
                modifier = Modifier.testTag("UsernameErrorText"),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            enabled = !loginUIState.isLoading,
            value = loginUIState.password,
            onValueChange = loginUIState.onChangePassword,
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().testTag("PasswordTextField"),
        )
        AnimatedVisibility(visible = loginUIState.errorPassword.isNotEmpty()) {
            Text(
                text = loginUIState.errorPassword,
                color = Color.Red,
                modifier = Modifier.testTag("PasswordErrorText"),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            enabled = !loginUIState.isLoading,
            onClick = loginUIState.onLogin,
            modifier = Modifier.fillMaxWidth().testTag("LoginButton"),
        ) {
            if (loginUIState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Login")
            }
        }
    }
}
