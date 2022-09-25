package com.iamgjm.fibonacidroid.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.base.presentation.Content
import com.iamgjm.fibonacidroid.base.presentation.PrimaryButton
import com.iamgjm.fibonacidroid.presentation.compose.primary

@Composable
fun LoginScreen(
    state: LoginState,
    onEmailChanged: (TextFieldValue) -> Unit,
    onPasswordChange: (TextFieldValue) -> Unit,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
) {
    Content {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 96.dp, end = 96.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.alpha(1f),
                    painter = painterResource(id = R.drawable.fd_logo),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(primary)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(
                        value = state.email,
                        onValueChange = onEmailChanged,
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

                    PrimaryButton(
                        onClick = onSignIn,
                        loading = state.loading,
                        enabled = state.buttonEnabled,
                        text = stringResource(id = R.string.sign_in)
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
                    modifier = Modifier.clickable { onSignUp() },
                    text = stringResource(id = R.string.no_account),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

data class LoginState(
    val loading: Boolean,
    val buttonEnabled: Boolean,
    val email: TextFieldValue,
    val password: TextFieldValue
)