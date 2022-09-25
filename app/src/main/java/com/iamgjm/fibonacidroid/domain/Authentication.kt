package com.iamgjm.fibonacidroid.domain

import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices

class Authentication {

    /**
     *
     */
    fun navigate() =
       FirebaseServices.auth().currentUser?.let {
           NavigationEvent.History
       } ?: NavigationEvent.Login
}