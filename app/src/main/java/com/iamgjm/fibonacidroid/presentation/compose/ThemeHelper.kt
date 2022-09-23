package com.iamgjm.fibonacidroid.presentation.compose

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.iamgjm.fibonacidroid.data.local.repository.Theme

object ThemeHelper {

    /**
     * Applies the chosen theme on the app.
     */
    fun apply(theme: Theme) {
        AppCompatDelegate.setDefaultNightMode(
            when (theme) {
                Theme.LIGHT -> MODE_NIGHT_NO
                Theme.DARK -> MODE_NIGHT_YES
                else -> MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
    }
}