package com.iamgjm.fibonacidroid.registration.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import com.iamgjm.fibonacidroid.base.presentation.BaseFragment
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme

class RegistrationFragment : BaseFragment() {

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = createViewModel { RegistrationViewModel(it) }

        return ComposeView(requireContext()).apply {
            setContent {
                FibonacciTheme {
                    Surface {
                        RegistrationScreen(
                            state = viewModel.state,
                            onUsernameChange = viewModel::onUsernameChange,
                            onEmailChange = viewModel::onEmailChange,
                            onPasswordChange = viewModel::onPasswordChange,
                            onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
                            onSignUp = viewModel::onSignUp,
                            onSignIn = viewModel::onSignIn
                        )
                    }
                }
            }
        }
    }

    override fun viewModel() = viewModel
}