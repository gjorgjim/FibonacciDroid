package com.iamgjm.fibonacidroid.registration.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.base.presentation.Content
import com.iamgjm.fibonacidroid.base.presentation.PrimaryButton

@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onUsernameChange: (TextFieldValue) -> Unit,
    onEmailChange: (TextFieldValue) -> Unit,
    onPasswordChange: (TextFieldValue) -> Unit,
    onRepeatPasswordChange: (TextFieldValue) -> Unit,
    onSignUp: () -> Unit,
    onSignIn: () -> Unit
) {
    Content {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(id = R.string.enter_credentials),
                        style = MaterialTheme.typography.h6
                    )

                    Spacer(modifier = Modifier.size(64.dp))

                    TextField(
                        value = state.username,
                        onValueChange = onUsernameChange,
                        enabled = !state.loading,
                        placeholder = {
                            Text(text = stringResource(id = R.string.username))
                        }
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    TextField(
                        value = state.email,
                        onValueChange = onEmailChange,
                        enabled = !state.loading,
                        placeholder = {
                            Text(text = stringResource(id = R.string.email))
                        }
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    TextField(
                        value = state.password,
                        onValueChange = onPasswordChange,
                        enabled = !state.loading,
                        placeholder = {
                            Text(text = stringResource(id = R.string.password))
                        },
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    TextField(
                        value = state.repeatPassword,
                        onValueChange = onRepeatPasswordChange,
                        enabled = !state.loading,
                        placeholder = {
                            Text(text = stringResource(id = R.string.repeatPassword))
                        },
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    PrimaryButton(
                        onClick = onSignUp,
                        loading = state.loading,
                        enabled = state.buttonEnabled,
                        text = stringResource(id = R.string.sign_up)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier.clickable { onSignIn() },
                    text = stringResource(id = R.string.already_have_account),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

data class RegistrationState(
    val loading: Boolean,
    val buttonEnabled: Boolean,
    val username: TextFieldValue,
    val email: TextFieldValue,
    val password: TextFieldValue,
    val repeatPassword: TextFieldValue
)