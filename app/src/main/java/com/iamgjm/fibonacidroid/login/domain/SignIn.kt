package com.iamgjm.fibonacidroid.login.domain

import android.util.Log
import com.iamgjm.fibonacidroid.base.Outcome
import com.iamgjm.fibonacidroid.base.mapResponse
import com.iamgjm.fibonacidroid.base.runFirebaseOperation
import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices
import com.iamgjm.fibonacidroid.presentation.NavigationEvent
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class SignIn {

    operator fun invoke(
        email: String,
        password: String
    ) = flow {
        emit(Result(loading = true))

        emit(signIn(email, password))
    }

    private suspend fun signIn(email: String, password: String) =
        runFirebaseOperation {
            FirebaseServices.auth().signInWithEmailAndPassword(email, password).await()
        }.mapResponse {
            when(it) {
                is Outcome.Error -> {
                    Log.e("Firebase", it.message)
                    Result(buttonEnabled = true, error = it.message)
                }
                is Outcome.Complete -> {
                    Result(event = NavigationEvent.History)
                }
            }
        }

    data class Result(
        val loading: Boolean = false,
        val buttonEnabled: Boolean = false,
        val error: String? = null,
        val event: NavigationEvent? = null
    )
}