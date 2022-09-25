package com.iamgjm.fibonacidroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return creator() as T
    }
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.viewModel(crossinline viewModelInstance: () -> T) : T =
    ViewModelProvider(this, ViewModelFactory { viewModelInstance() }).get(T::class.java)

inline fun <reified T : ViewModel> ViewModelStoreOwner.noConstructorViewModel() : T =
    ViewModelProvider(this).get(T::class.java)