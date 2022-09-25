package com.iamgjm.fibonacidroid.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

sealed class Outcome<out T> {

    class Complete<T>(val value: T) : Outcome<T>()

    class Error(val message: String) : Outcome<Nothing>()
}

suspend fun <K> runFirebaseOperation(block: suspend () -> K) : Outcome<K> =
    try {
        withContext(Dispatchers.IO) {
            Outcome.Complete(block())
        }
    } catch (e: Exception) {
        Outcome.Error(e.message ?: technicalError())
    }

fun <A, B> A.mapResponse(transform: (A) -> B) : B = transform(this)

suspend fun <A, B> A.mapSuspended(transform: suspend (A) -> B) : B = transform(this)

fun technicalError() = "Unexpected Error"