package com.iamgjm.fibonacidroid.base.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.iamgjm.fibonacidroid.base.viewModel
import com.iamgjm.fibonacidroid.presentation.FibonacciSharedViewModel
import com.iamgjm.fibonacidroid.presentation.IShared

abstract class BaseFragment : Fragment() {

    protected val sharedViewModel: FibonacciSharedViewModel by activityViewModels()

    /**
     *
     */
    fun goBack() {
        viewModel().goBack()
    }

    protected inline fun <reified T : ViewModel> createViewModel(crossinline create: (sharedViewModel: IShared) -> T) =
        viewModel{ create(sharedViewModel) }

    abstract fun viewModel() : BaseViewModel
}