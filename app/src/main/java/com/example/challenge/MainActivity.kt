package com.example.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

