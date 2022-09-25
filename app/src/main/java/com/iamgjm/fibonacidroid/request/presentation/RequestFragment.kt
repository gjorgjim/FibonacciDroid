package com.iamgjm.fibonacidroid.request.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import com.iamgjm.fibonacidroid.base.presentation.BaseFragment
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme

class RequestFragment : BaseFragment() {

    private lateinit var viewModel: RequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = createViewModel { RequestViewModel(it) }

        return ComposeView(requireContext()).apply {
            setContent {
                FibonacciTheme {
                    Surface {
                        RequestScreen(
                            state = viewModel.state,
                            onInputChange = viewModel::onInputChange,
                            onCalculate = viewModel::onCalculate,
                            onSignOut = viewModel::signOut
                        )
                    }
                }
            }
        }
    }

    override fun viewModel() = viewModel
}