package com.iamgjm.fibonacidroid.login.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.iamgjm.fibonacidroid.base.presentation.BaseViewModel
import com.iamgjm.fibonacidroid.login.domain.LoginFormValidation
import com.iamgjm.fibonacidroid.login.domain.SignIn
import com.iamgjm.fibonacidroid.presentation.IShared
import com.iamgjm.fibonacidroid.presentation.NavigationEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(sharedViewModel: IShared) : BaseViewModel(sharedViewModel) {

    private var _state by mutableStateOf(
        LoginState(
            loading = false,
            buttonEnabled = false,
            email = TextFieldValue(),
            password = TextFieldValue()
        )
    )
    val state: LoginState
        get() = _state

    private val loginFormValidation = LoginFormValidation()
    private val signIn = SignIn()

    fun onEmailChanged(value: TextFieldValue) {
        updateState { copy(email = value, buttonEnabled = buttonEnabled(email = value.text)) }
    }

    fun onPasswordChanged(value: TextFieldValue) {
        updateState { copy(password = value, buttonEnabled = buttonEnabled(password = value.text)) }
    }

    fun onSignIn() {
        signIn(_state.email.text, _state.password.text).onEach {
            updateState { copy(loading = it.loading, buttonEnabled = it.buttonEnabled) }
            it.event?.let(goTo)
        }.launchIn(viewModelScope)
    }

    fun onSignUp() {
        goTo(NavigationEvent.Register)
    }

    override fun goBack() {
        goTo(NavigationEvent.Finish)
    }

    private fun buttonEnabled(email: String = _state.email.text, password: String = _state.password.text) =
        loginFormValidation.showButton(email, password)

    private fun updateState(updateState: LoginState.() -> LoginState) {
        _state = _state.updateState()
    }
}