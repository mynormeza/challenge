package com.americanassist.bykemap_test.ui.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.challenge.presentation.uistate.LoginUIState
import com.example.challenge.ui.screen.LoginForm
import org.junit.Rule
import org.junit.Test

class LoginScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginButton_showsError_whenUsernameIsEmpty() {
        lateinit var loginUIState: MutableState<LoginUIState>

        composeTestRule.setContent {
            loginUIState =
                remember {
                    mutableStateOf(
                        LoginUIState(
                            onLogin = {
                                loginUIState.value =
                                    loginUIState.value.copy(errorUsername = "Username is required")
                            },
                        ),
                    )
                }

            LoginForm(loginUIState = loginUIState.value)
        }

        composeTestRule.onNodeWithTag("LoginButton").performClick()

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("UsernameErrorText")
            .assertExists()
            .assertTextEquals("Username is required")
    }
}
