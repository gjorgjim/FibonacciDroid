package com.iamgjm.fibonacidroid.data.local.repository

class ThemeStorage {

    companion object {
        private const val THEME_MODE = "theme_mode"
    }

    /**
     *
     */
    fun save(theme: Theme) {
        LocalStorageManager.store(THEME_MODE, theme.value)
    }

    /**
     *
     */
    fun load() =
        LocalStorageManager.load(THEME_MODE).asTheme()
}

enum class Theme(val value: String) {
    DEFAULT("default"),
    LIGHT("light"),
    DARK("dark")
}

fun String.asTheme() = when (this) {
    "light" -> Theme.LIGHT
    "dark" -> Theme.DARK
    else -> Theme.DEFAULT
}