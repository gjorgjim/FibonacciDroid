package com.iamgjm.fibonacidroid.data.local.repository

import android.content.Context
import com.iamgjm.fibonacidroid.App

object LocalStorageManager {

    private const val PREFS_NAME = "com.iamgjm.fibonacci"
    private const val DEFAULT = "default"

    /**
     *
     */
    fun store(key: String, value: String) {
        storage().edit().putString(key, value).apply()
    }

    /**
     *
     */
    fun load(key: String) =
        storage().getString(key, DEFAULT) ?: DEFAULT

    private fun storage() =
        App.instance().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}