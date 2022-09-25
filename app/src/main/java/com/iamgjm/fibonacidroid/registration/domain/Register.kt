package com.iamgjm.fibonacidroid.registration.domain

import android.util.Log
import com.iamgjm.fibonacidroid.base.Outcome
import com.iamgjm.fibonacidroid.base.mapResponse
import com.iamgjm.fibonacidroid.base.mapSuspended
import com.iamgjm.fibonacidroid.base.runFirebaseOperation
import com.iamgjm.fibonacidroid.base.technicalError
import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices
import com.iamgjm.fibonacidroid.presentation.NavigationEvent
import com.iamgjm.fibonacidroid.registration.data.User
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class Register {

    /**
     *
     */
    operator fun invoke(
        email: String,
        password: String,
        username: String
    ) = flow {
        emit(Result(loading = true))

        emit(register(email, password, username))
    }

    private suspend fun register(email: String, password: String, username: String) : Result =
        runFirebaseOperation {
            FirebaseServices.auth().createUserWithEmailAndPassword(email, password).await()
        }.mapSuspended { result ->
            when (result) {
                is Outcome.Error -> {
                    Log.e("Firebase", result.message)
                    Result(buttonEnabled = true, error = result.message)
                }
                is Outcome.Complete -> {
                    result.value.user?.let {
                        saveUser(it.uid, email, password, username)
                    } ?: Result(buttonEnabled = true, error = technicalError())
                }
            }
        }

    private suspend fun saveUser(userId: String, email: String, password: String, username: String) : Result =
        runFirebaseOperation {
            FirebaseServices.db()
                .child("users")
                .child(userId)
                .setValue(
                    User(
                        username, email
                    )
                ).await()
        }.mapResponse {
            when(it) {
                is Outcome.Error -> {
                    Log.e("Firebase", it.message)
                    Result(buttonEnabled = true, error = it.message)
                }
                is Outcome.Complete -> Result(event = NavigationEvent.History)
            }
        }

    data class Result(
        val loading: Boolean = false,
        val buttonEnabled: Boolean = false,
        val error: String? = null,
        val event: NavigationEvent? = null
    )
}