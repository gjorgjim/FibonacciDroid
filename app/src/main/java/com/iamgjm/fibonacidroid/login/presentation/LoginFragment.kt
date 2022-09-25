package com.iamgjm.fibonacidroid.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import com.iamgjm.fibonacidroid.base.presentation.BaseFragment
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme

class LoginFragment : BaseFragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = createViewModel { LoginViewModel(it) }

        return ComposeView(requireContext()).apply {
            setContent {
                FibonacciTheme {
                    Surface {
                        LoginScreen(
                            state = viewModel.state,
                            onEmailChanged = viewModel::onEmailChanged,
                            onPasswordChange = viewModel::onPasswordChanged,
                            onSignIn = viewModel::onSignIn,
                            onSignUp = viewModel::onSignUp
                        )
                    }
                }
            }
        }
    }

    override fun viewModel() = viewModel
}