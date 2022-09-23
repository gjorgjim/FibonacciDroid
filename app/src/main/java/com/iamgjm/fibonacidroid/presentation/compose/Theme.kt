package com.iamgjm.fibonacidroid.presentation.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = FibonacciColors(
    materialColors = darkColors(
        primary = primary,
        secondary = secondary
    ),
    primaryText = darkPrimaryText,
    secondaryText = secondaryText
)

private val LightColorPalette = FibonacciColors(
    materialColors = lightColors(
        primary = primary,
        secondary = secondary
    ),
    primaryText = lightPrimaryText,
    secondaryText = secondaryText
)

@Composable
fun FibonacciTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if(darkTheme)
        DarkColorPalette
    else
        LightColorPalette

    ProvideFibonacciColors(colors) {
        MaterialTheme(
            colors = colors.material,
            content = content
        )
    }
}

object FibonacciTheme {
    val colors: FibonacciColors
        @Composable
        get() = LocalFibonacciColors.current
}

class FibonacciColors(
    materialColors: Colors,
    primaryText: Color,
    secondaryText: Color
) {
    var material = materialColors
        private set
    var primary = material.primary
    var secondary = material.secondary

    var primaryText by mutableStateOf(primaryText)
        private set
    var secondaryText by mutableStateOf(secondaryText)
        private set

    fun update(other: FibonacciColors) {
        material = other.material
        primaryText = other.primaryText
        secondaryText = other.secondaryText
    }
}

@Composable
fun ProvideFibonacciColors(
    colors: FibonacciColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalFibonacciColors provides colorPalette, content = content)
}

private val LocalFibonacciColors =
    staticCompositionLocalOf<FibonacciColors> { throw IllegalStateException("No colors provided") }