package com.iamgjm.fibonacidroid.domain

import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices
import com.iamgjm.fibonacidroid.presentation.NavigationEvent

class Authentication {

    /**
     *
     */
    fun navigate() =
       FirebaseServices.auth().currentUser?.let {
           NavigationEvent.History
       } ?: NavigationEvent.Login
}