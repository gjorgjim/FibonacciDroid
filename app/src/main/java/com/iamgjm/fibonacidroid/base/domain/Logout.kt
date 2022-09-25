package com.iamgjm.fibonacidroid.base.domain

import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices

class Logout {

    /**
     *
     */
    operator fun invoke() {
        FirebaseServices.auth().signOut()
    }
}