package com.iamgjm.fibonacidroid.registration.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.iamgjm.fibonacidroid.base.presentation.BaseViewModel
import com.iamgjm.fibonacidroid.presentation.IShared
import com.iamgjm.fibonacidroid.domain.NavigationEvent
import com.iamgjm.fibonacidroid.registration.domain.Register
import com.iamgjm.fibonacidroid.registration.domain.RegisterFormValidation
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegistrationViewModel(sharedViewModel: IShared) : BaseViewModel(sharedViewModel) {

    private var _state by mutableStateOf(
        RegistrationState(
            loading = false,
            buttonEnabled = false,
            username = TextFieldValue(),
            email = TextFieldValue(),
            password = TextFieldValue(),
            repeatPassword = TextFieldValue()
        )
    )
    val state: RegistrationState
        get() = _state

    private val registerFormValidation = RegisterFormValidation()
    private val register = Register()

    fun onUsernameChange(value: TextFieldValue) {
        updateState { copy(username = value) }
    }

    fun onEmailChange(value: TextFieldValue) {
        updateState { copy(email = value, buttonEnabled = buttonEnabled(email = value.text)) }
    }

    fun onPasswordChange(value: TextFieldValue) {
        updateState { copy(password = value, buttonEnabled = buttonEnabled(password = value.text)) }
    }

    fun onRepeatPasswordChange(value: TextFieldValue) {
        updateState { copy(repeatPassword = value, buttonEnabled = buttonEnabled(repeatPassword = value.text)) }
    }

    fun onSignUp() {
            register(
                _state.email.text,
                _state.password.text,
                _state.username.text
            ).onEach {
                updateState { copy(
                    loading = it.loading,
                    buttonEnabled = it.buttonEnabled
                ) }
                it.event?.let(goTo)
            }.launchIn(viewModelScope)
    }

    fun onSignIn() {
        goTo(NavigationEvent.Login)
    }

    override fun goBack() {
        goTo(NavigationEvent.Login)
    }

    private fun buttonEnabled(
        email: String = _state.email.text,
        password: String = _state.password.text,
        repeatPassword: String = _state.repeatPassword.text
    ) =
        registerFormValidation.showButton(
            email, password, repeatPassword
        )

    private fun updateState(updateState: RegistrationState.() -> RegistrationState) {
        _state = _state.updateState()
    }
}