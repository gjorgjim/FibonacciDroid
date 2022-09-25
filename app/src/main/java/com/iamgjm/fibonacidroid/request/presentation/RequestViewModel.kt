package com.iamgjm.fibonacidroid.request.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.iamgjm.fibonacidroid.base.presentation.BaseViewModel
import com.iamgjm.fibonacidroid.presentation.IShared
import com.iamgjm.fibonacidroid.domain.NavigationEvent
import com.iamgjm.fibonacidroid.request.domain.FibonacciSequence
import com.iamgjm.fibonacidroid.request.domain.NumberValidator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RequestViewModel(sharedViewModel: IShared) : BaseViewModel(sharedViewModel) {

    private var _state by mutableStateOf(
        RequestState(
            loading = false,
            input = TextFieldValue(),
            buttonEnabled = false,
            sequence = listOf(
            )
        )
    )
    val state: RequestState
        get() = _state

    private val fibonacciSequence = FibonacciSequence()
    private val numberValidator = NumberValidator()

    fun onInputChange(value: TextFieldValue) {
        updateState { copy(input = value, buttonEnabled = numberValidator(value.text) ) }
    }

    fun onCalculate() {
        fibonacciSequence(_state.input.text.toInt()).onEach {
            updateState { copy(loading = it.loading, buttonEnabled = it.buttonEnabled, sequence = it.sequence) }
        }.launchIn(viewModelScope)
    }

    override fun goBack() {
        goTo(NavigationEvent.History)
    }

    private fun updateState(updateState: RequestState.() -> RequestState) {
        _state = _state.updateState()
    }
}