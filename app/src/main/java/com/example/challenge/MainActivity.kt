package com.example.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.challenge.presentation.uistate.LoginUIState
import com.example.challenge.presentation.viewmodel.LoginViewModel
import com.example.challenge.ui.screen.LoginScreen
import com.example.challenge.ui.theme.ChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContent {
                val loginViewModel = LoginViewModel()
                val loginUIState: LoginUIState by loginViewModel.uiState.collectAsState()
                ChallengeTheme {
                    LoginScreen(loginUIState)
                }
            }
        }
    }
}
