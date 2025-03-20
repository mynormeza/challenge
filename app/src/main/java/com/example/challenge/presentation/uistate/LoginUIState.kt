package com.example.challenge.presentation.uistate

data class LoginUIState(
    val isLoading: Boolean = false,
    val snackMessage: String? = null,
    val username: String = "",
    val errorUsername: String = "",
    val password: String = "",
    val errorPassword: String = "",
    val onChangeUsername: (String) -> Unit = {},
    val onChangePassword: (String) -> Unit = {},
    val onLogin: () -> Unit = {},
    val result: String = ""
)