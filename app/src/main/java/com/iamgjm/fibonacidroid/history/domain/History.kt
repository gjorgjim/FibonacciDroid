package com.iamgjm.fibonacidroid.history.domain

import com.google.firebase.database.DataSnapshot
import com.iamgjm.fibonacidroid.base.Outcome
import com.iamgjm.fibonacidroid.base.mapResponse
import com.iamgjm.fibonacidroid.base.runFirebaseOperation
import com.iamgjm.fibonacidroid.base.technicalError
import com.iamgjm.fibonacidroid.data.firebase.FirebaseServices
import com.iamgjm.fibonacidroid.history.data.HistoryItem
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date

class History {

    operator fun invoke() = flow {
        emit(Result(loading = true))

        emit(readHistory())
    }

    private suspend fun readHistory() : Result {
        val user = FirebaseServices.auth().currentUser?.uid ?: return Result(error = technicalError())
        return runFirebaseOperation {
            FirebaseServices.db().child("users").child(user).child("history").get().await()
        }.mapResponse {
            when(it) {
                is Outcome.Error -> Result(error = it.message)
                is Outcome.Complete -> Result(data = mapData(it.value))
            }
        }
    }

    private fun mapData(data: DataSnapshot) : List<Pair<List<Int>, String>> {
        val format = SimpleDateFormat("dd/MM/yyy")
        val result = mutableListOf<Pair<List<Int>, String>>()
        if (data.value == null) return result

        (data.value as? HashMap<*, *>)?.let { historyMap ->
            historyMap.entries.forEach {
                val historyData = it.value as? HashMap<*, *>
                val historyItem = HistoryItem(
                    sequence = historyData?.get("sequence") as? String ?: "",
                    date = historyData?.get("date") as? Long ?: -1
                )
                result.add(
                    Pair(
                        historyItem.sequence.split(",").map { it.toInt() },
                        format.format(Date(historyItem.date))
                    )
                )
            }
        }

        return result
    }

    data class Result(
        val loading: Boolean = false,
        val error: String? = null,
        val data: List<Pair<List<Int>, String>> = listOf()
    )
}