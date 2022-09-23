package com.iamgjm.fibonacidroid

import android.app.Application
import com.iamgjm.fibonacidroid.data.local.repository.ThemeStorage
import com.iamgjm.fibonacidroid.presentation.compose.ThemeHelper

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun instance(): App {
            return instance as App
        }
    }

    override fun onCreate() {
        super.onCreate()

        ThemeHelper.apply(ThemeStorage().load())
    }
}