package com.iamgjm.fibonacidroid.presentation

sealed class NavigationEvent {
    object Login : NavigationEvent()

    object Register : NavigationEvent()

    object History: NavigationEvent()

    object Request : NavigationEvent()

    object Finish: NavigationEvent()
}