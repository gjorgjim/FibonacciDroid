package com.iamgjm.fibonacidroid.base.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.iamgjm.fibonacidroid.R
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme

@Composable
fun Content(
    withToolbar: Boolean = false,
    onSignOut: (() -> Unit)? = null,
    content: @Composable  () -> Unit
) {
    Image(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.05f),
        painter = painterResource(id = R.drawable.background),
        contentScale = ContentScale.FillHeight,
        contentDescription = ""
    )

    Column(modifier = Modifier.fillMaxSize()) {
        if (withToolbar) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(FibonacciTheme.colors.toolbar)
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(end = 24.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.clickable { onSignOut?.invoke() },
                        imageVector = Icons.Filled.Logout,
                        contentDescription = ""
                    )
                }
            }
        }

        content()
    }
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    loading: Boolean,
    enabled: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.padding(16.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        if (loading) {
            CircularProgressIndicator()
            return@Button
        }
        Text(text = text)
    }
}