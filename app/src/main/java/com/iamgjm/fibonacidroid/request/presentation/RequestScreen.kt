package com.iamgjm.fibonacidroid.request.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.base.presentation.Content
import com.iamgjm.fibonacidroid.base.presentation.PrimaryButton
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme

@Composable
fun RequestScreen(
    state: RequestState,
    onInputChange: (TextFieldValue) -> Unit,
    onCalculate: () -> Unit,
    onSignOut: () -> Unit
) {
    Content(withToolbar = true, onSignOut) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.calculate_new),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.size(24.dp))

                TextField(
                    value = state.input,
                    onValueChange = onInputChange,
                    enabled = !state.loading,
                    placeholder = {
                        Text(text = stringResource(id = R.string.fibonacci_number))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.size(24.dp))

                PrimaryButton(
                    onClick = onCalculate,
                    loading = state.loading,
                    enabled = state.buttonEnabled,
                    text = stringResource(id = R.string.calculate)
                )

                if (state.sequence.isNotEmpty()) {
                    Spacer(modifier = Modifier.size(24.dp))

                    Text(
                        text = stringResource(id = R.string.result),
                        style = MaterialTheme.typography.h6,
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    val number = state.sequence.last()
                    val modifiedSequence = state.sequence.toMutableList()
                    modifiedSequence.removeLast()
                    val sequenceText = modifiedSequence.joinToString()

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = sequenceText,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.size(16.dp))


                    Text(
                        modifier = Modifier
                            .padding(
                                top = 8.dp,
                                bottom = 8.dp,
                                start = 32.dp,
                                end = 32.dp
                            ),
                        text = number.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h2,
                    )
                }
            }
        }
    }
}

data class RequestState(
    val loading: Boolean,
    val input: TextFieldValue,
    val buttonEnabled: Boolean,
    val sequence: List<Int>
)