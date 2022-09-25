package com.iamgjm.fibonacidroid.presentation.compose

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.iamgjm.fibonacidroid.R

val InterTight = FontFamily(
    Font(R.font.intertight_regular),
    Font(R.font.interright_medium, FontWeight.Medium),
    Font(R.font.intertight_bold, FontWeight.Bold)
)

val FibonacciTypography = Typography(
    defaultFontFamily = InterTight
)