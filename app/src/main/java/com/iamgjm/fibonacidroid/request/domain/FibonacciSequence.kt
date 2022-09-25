package com.iamgjm.fibonacidroid.request.domain

import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices
import com.iamgjm.fibonacidroid.history.data.HistoryItem
import kotlinx.coroutines.flow.flow
import java.util.Date
import java.util.UUID

class FibonacciSequence {

    operator fun invoke(number: Int) = flow {
        emit(Result(loading = true))

        val result = fibonacci(number)

        emit(Result(sequence = result))

        saveData(sequence = result)
    }

    private fun fibonacci(number: Int): List<Int> {
        val result = mutableListOf(0)

        if (number == 1) return result

        result.add(1)

        if (number == 2) return result

        result.add(1)

        if (number == 3) return result

        for(i in 4..number) {
            result.add(result[result.size - 1] + result[result.size - 2])
        }

        return result
    }

    private fun saveData(sequence: List<Int>) {
        val user = FirebaseServices.auth().currentUser?.uid ?: return
        FirebaseServices.db()
            .child("users")
            .child(user)
            .child("history")
            .child(UUID.randomUUID().toString())
            .setValue(
                HistoryItem(
                    sequence.joinToString(","),
                    Date().time
                )
            )
    }

    data class Result(
        val loading: Boolean = false,
        val buttonEnabled: Boolean = false,
        val sequence: List<Int> = listOf()
    )
}