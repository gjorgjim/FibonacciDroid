package com.iamgjm.fibonacidroid.history.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import com.iamgjm.fibonacidroid.base.presentation.BaseFragment
import com.iamgjm.fibonacidroid.presentation.compose.FibonacciTheme

class HistoryFragment : BaseFragment() {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = createViewModel { HistoryViewModel(it) }

        return ComposeView(requireContext()).apply {
            setContent {
                FibonacciTheme {
                    Surface {
                        HistoryScreen(
                            state = viewModel.state,
                            onRequest = viewModel::onRequest,
                            onSignOut = viewModel::signOut
                        )
                    }
                }
            }
        }
    }

    override fun viewModel() = viewModel
}