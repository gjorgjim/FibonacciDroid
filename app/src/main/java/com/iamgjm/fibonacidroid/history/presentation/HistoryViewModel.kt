package com.iamgjm.fibonacidroid.history.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.iamgjm.fibonacidroid.base.presentation.BaseViewModel
import com.iamgjm.fibonacidroid.history.domain.History
import com.iamgjm.fibonacidroid.history.fibonacciitem.presentation.FibonacciItemData
import com.iamgjm.fibonacidroid.presentation.IShared
import com.iamgjm.fibonacidroid.domain.NavigationEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HistoryViewModel(sharedViewModel: IShared) : BaseViewModel(sharedViewModel) {

    private var _state by mutableStateOf(
        HistoryState(
            loading = false,
            data = listOf()
        )
    )
    val state: HistoryState
        get() = _state

    private val history = History()

    init {
        history().onEach {
            updateState { copy(loading = it.loading, data = it.data.map { FibonacciItemData(it.first, it.second) }) }
        }.launchIn(viewModelScope)
    }

    fun onRequest() {
        goTo(NavigationEvent.Request)
    }

    override fun goBack() {
        goTo(NavigationEvent.Finish)
    }

    private fun updateState(updateState: HistoryState.() -> HistoryState) {
        _state = _state.updateState()
    }
}