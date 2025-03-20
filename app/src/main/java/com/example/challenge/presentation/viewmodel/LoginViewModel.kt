package com.example.challenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.data.models.ErrorType
import com.example.challenge.data.usecase.LoginUseCase
import com.example.challenge.data.usecase.LoginUseCaseImpl
import com.example.challenge.presentation.uistate.LoginUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel constructor(
    private val loginUseCase: LoginUseCase = LoginUseCaseImpl(),
) : ViewModel() {
    private val _uiState: MutableStateFlow<LoginUIState> =
        MutableStateFlow(
            LoginUIState(
                onChangeUsername = ::onChangeUsername,
                onChangePassword = ::onChangePassword,
                onLogin = ::onLogin,
            ),
        )
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    private fun onChangeUsername(username: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(username = username, errorUsername = "")
            }
        }
    }

    private fun onChangePassword(password: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(password = password, errorPassword = "")
            }
        }
    }

    private fun onLogin() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val username = uiState.value.username
            val password = uiState.value.password

            when {
                username.isEmpty() && password.isEmpty() -> {
                    _uiState.update {
                        it.copy(
                            errorUsername = "Username is required",
                            errorPassword = "Password is required",
                            isLoading = false,
                        )
                    }
                }

                username.isEmpty() -> {
                    _uiState.update {
                        it.copy(errorUsername = "Username is required", isLoading = false)
                    }
                }

                password.isEmpty() -> {
                    _uiState.update {
                        it.copy(errorPassword = "Password is required", isLoading = false)
                    }
                }

                else -> {
                    delay(1000)
                    val result = loginUseCase.executeLogin(username, password)

                    result.fold({
                        _uiState.update {
                            it.copy(result = "Login success")
                        }
                    }, {
                        val msgError =
                            when (it) {
                                ErrorType.WRONG_CREDENTIALS -> {
                                    "Wrong credentials"
                                }

                                ErrorType.INTERNAL_SERVER_ERROR -> {
                                    "Internal server error"
                                }

                                ErrorType.UNKNOWN_ERROR -> {
                                    "Unknown error"
                                }
                            }
                        _uiState.update {
                            it.copy(result = msgError, isLoading = false)
                        }
                    })
                }
            }

            _uiState.update {
                it.copy()
            }
        }
    }
}
