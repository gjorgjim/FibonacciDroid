package com.iamgjm.fibonacidroid.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object FirebaseServices {

    private var auth: FirebaseAuth? = null
    private var db: DatabaseReference? = null

    /**
     *
     */
    fun auth(): FirebaseAuth {
        if (auth == null) {
            auth = Firebase.auth
        }

        return auth as FirebaseAuth
    }

    /**
     *
     */
    fun db() : DatabaseReference {
        if (db == null) {
            db = Firebase.database("https://fibonaccidroid-default-rtdb.europe-west1.firebasedatabase.app").reference
        }

        return db as DatabaseReference
    }
}