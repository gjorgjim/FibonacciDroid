package com.iamgjm.fibonacidroid.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.history.fibonacciitem.presentation.FibonacciItem
import com.iamgjm.fibonacidroid.history.fibonacciitem.presentation.FibonacciItemData
import com.iamgjm.fibonacidroid.base.presentation.Content

@Composable
fun HistoryScreen(
    state: HistoryState,
    onRequest: () -> Unit,
    onSignOut: () -> Unit
) {
    Content(withToolbar = true, onSignOut) {
        when {
            state.loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
            state.data.isEmpty() -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(36.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.empty_history),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            }
            else -> LazyColumn {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        text = stringResource(id = R.string.history),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
                itemsIndexed(state.data) { index, item ->
                    FibonacciItem(data = item)
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(id = R.string.request))
                },
                onClick = onRequest
            )
        }
    }
}

data class HistoryState(
    val loading: Boolean,
    val data: List<FibonacciItemData>
)